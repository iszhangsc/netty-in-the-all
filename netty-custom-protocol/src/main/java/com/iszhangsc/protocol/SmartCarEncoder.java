package com.iszhangsc.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * <p>
 *  自定义编码器
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/3 下午4:48
 */
public class SmartCarEncoder extends MessageToByteEncoder<SmartCarProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, SmartCarProtocol msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getHeadData());
        out.writeInt(msg.getContentLength());
        out.writeBytes(msg.getContent());
    }

}
