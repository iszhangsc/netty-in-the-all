package com.iszhangsc.handler;

import com.iszhangsc.protocol.SmartCarProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/3 下午4:58
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // 用于获取客户端发来的数据信息
        SmartCarProtocol body = (SmartCarProtocol) msg;
        System.out.println("Server接受的客户端的信息 :" + body.toString());

        // 会写数据给客户端
        String str = "Hi I am Server ...";
        SmartCarProtocol response = new SmartCarProtocol(str.getBytes().length,
                str.getBytes());
        // 当服务端完成写操作后，关闭与客户端的连接
        ctx.writeAndFlush(response);
        // .addListener(ChannelFutureListener.CLOSE);

        // 当有写操作时，不需要手动释放msg的引用
        // 当只有读操作时，才需要手动释放msg的引用
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        // cause.printStackTrace();
        ctx.close();
    }

}
