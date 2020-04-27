package io.chat.agreement.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * <p>
 *  对象解码器
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 下午5:36
 */
public class ObjDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in,
                          List<Object> list) throws Exception {
        // 可读长度小于 4 in.readInt() 方法会数组越界
        if (in.readableBytes() < 4) {
            return;
        }

        in.markReaderIndex();
        int dataLength = in.readInt();
        // 可读字节长度小于
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }

        // 指令
        final byte command = in.readByte();
        // 指令占了一位，移除掉
        byte[] data = new byte[dataLength - 1];
        in.readBytes(data);
    }

}
