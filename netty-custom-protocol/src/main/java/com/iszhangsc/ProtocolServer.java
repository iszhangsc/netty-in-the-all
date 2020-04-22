package com.iszhangsc;

import com.iszhangsc.handler.ServerHandler;
import com.iszhangsc.protocol.SmartCarDecoder;
import com.iszhangsc.protocol.SmartCarEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/3 下午4:54
 */
public class ProtocolServer {

    public ProtocolServer() {

    }

    public void bind(int port) throws InterruptedException {
        // 配置NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 服务器辅助启动类配置
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChildChannelHandler())
                    // 设置tcp缓冲区
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            // 绑定端口 同步等待绑定成功
            ChannelFuture f = b.bind(port).sync();
            // 等到服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅释放线程资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    private static class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            final ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast(new SmartCarEncoder());
            pipeline.addLast(new SmartCarDecoder());
            pipeline.addLast(new ServerHandler());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ProtocolServer().bind(9999);
    }

}
