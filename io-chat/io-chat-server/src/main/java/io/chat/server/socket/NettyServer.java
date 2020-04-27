package io.chat.server.socket;

import io.chat.server.service.UserService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * <p>
 *  netty 服务端
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 8:42 下午
 */
@Slf4j
@Service
public class NettyServer implements Callable<Channel> {

    @Autowired
    private UserService userService;

    public NettyServer(UserService userService) {
        this.userService = userService;
    }

    private final EventLoopGroup boosGroup = new NioEventLoopGroup(2);
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;


    @Override
    public Channel call() throws Exception {
        ChannelFuture channelFuture = null;
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new MyChannelInitializer(userService));
            channelFuture = server.bind(new InetSocketAddress(7393)).syncUninterruptibly();
            this.channel = channelFuture.channel();
        } catch (Exception e) {
            log.error("socket server start error :{}", Arrays.toString(e.getStackTrace()));
        } finally {
            if (null != channelFuture && channelFuture.isSuccess()) {
                log.info("socket server start done. ");
            } else {
                log.error("socket server start error. ");
            }
        }
        return channel;
    }

    public void destroy() {
        if (channel == null) {
            return;
        }
        channel.close();
        boosGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    public Channel channel() {
        return channel;
    }

}
