package io.chat.agreement.codec;

import io.chat.agreement.protocol.Packet;
import io.chat.agreement.util.SerializationUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

/**
 * <p>
 * 编码器
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 8:02 下午
 */
public class ObjEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf out) throws Exception {
        // 序列化成字节 数组
        byte[] data = SerializationUtils.serialize(packet);
        // 数据包长度 = 数据长度+ 指令长度
        out.writeInt(data.length + 1);
        // 添加指令
        out.writeByte(packet.getCommand());
        out.writeBytes(data);
    }

}
