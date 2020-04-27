package io.chat.agreement.util;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *  序列化工具类
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 下午5:13
 */
public class SerializationUtils {

    private SerializationUtils() {

    }


    private static final Map<Class<?>, Schema<?>> CACHED_SCHEMA = new ConcurrentHashMap<>();


    private static final Objenesis OBJENESIS = new ObjenesisStd();


    /**
     * 序列化（对象--> 字节数组）
     * @param obj   对象
     * @param <T>   对象泛型
     * @return  字节数组.
     */
    @SuppressWarnings("unchecked")
    public static <T> byte[] serialize(T obj) {
        Class<T> clazz = (Class<T>) obj.getClass();
        final LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            final Schema<T> schema = getSchema(clazz);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化（字节数组-->  对象）
     * @param data  字节数组
     * @param clazz 对象
     * @param <T>   对象泛型
     * @return  T
     */
    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        try {
            T message = OBJENESIS.newInstance(clazz);
            Schema<T> schema = getSchema(clazz);
            ProtostuffIOUtil.mergeFrom(data, message, schema);
            return message;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }


    /**
     * 获取Schema对象
     * @param clazz 对象
     * @param <T>   对象泛型
     * @return  Schema
     */
    @SuppressWarnings("unchecked")
    private static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) CACHED_SCHEMA.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(clazz);
            CACHED_SCHEMA.put(clazz, schema);
        }
        return schema;
    }

}
