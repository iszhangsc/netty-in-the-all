package io.netty.example.study.common;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.nio.charset.StandardCharsets;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/21 下午2:12
 */
@Data
public abstract class BaseMessage<T extends BaseMessageBody> {


    private MessageHeader messageHeader;
    private T messageBody;

    /**
     * 编码
     * @param byteBuf
     */
    public void encode(ByteBuf byteBuf) {
        byteBuf.writeInt(messageHeader.getVersion());
        byteBuf.writeLong(messageHeader.getStreamId());
        byteBuf.writeInt(messageHeader.getOpCode());
        byteBuf.writeBytes(JSON.toJSONBytes(messageBody));
    }

    /**
     * 获取消息体的编码操作类型
     * @param opCode
     * @return
     */
    public abstract Class<T> getMessageBodyDecodeClass(int opCode);

    /**
     * 解码
     * @param msg
     */
    public void decode(ByteBuf msg) {
        int version = msg.readInt();
        long streamId = msg.readLong();
        int opCode = msg.readInt();
        this.messageHeader = new MessageHeader(version, streamId, opCode);

        final Class<T> bodyDecodeClass = getMessageBodyDecodeClass(opCode);
        this.messageBody = JSON.parseObject(msg.toString(StandardCharsets.UTF_8), bodyDecodeClass);
    }

}
