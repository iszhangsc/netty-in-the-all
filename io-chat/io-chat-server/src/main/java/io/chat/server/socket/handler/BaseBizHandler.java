package io.chat.server.socket.handler;

import io.chat.server.infrastructure.common.SocketChannelUtils;
import io.chat.server.service.UserService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;

import java.io.PipedReader;

/**
 * <p>
 * 基础业务拦截器
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 8:57 下午
 */
@Slf4j
public abstract class BaseBizHandler<T> extends SimpleChannelInboundHandler<T> {

    protected UserService userService;

    public BaseBizHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {
        channelRead(ctx.channel(), msg);
    }

    /**
     * 子类实现重写该方法
     * @param channel   通道
     * @param msg       消息对象
     */
    public abstract void channelRead(Channel channel, T msg);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("客户端连接通知: {}", ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        SocketChannelUtils.removeChannel(ctx.channel().id().toString());
        SocketChannelUtils.removeChannelGroupByChannel(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("服务端异常断开:{}", cause.getMessage());
        SocketChannelUtils.removeChannel(ctx.channel().id().toString());
        SocketChannelUtils.removeChannelGroupByChannel(ctx.channel());
    }
}
