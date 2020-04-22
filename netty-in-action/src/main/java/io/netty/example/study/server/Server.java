package io.netty.example.study.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.example.study.server.codec.OrderFrameDecoder;
import io.netty.example.study.server.codec.OrderFrameEncoder;
import io.netty.example.study.server.codec.OrderProtocolDecoder;
import io.netty.example.study.server.codec.OrderProtocolEncoder;
import io.netty.example.study.server.codec.handler.MetricHandler;
import io.netty.example.study.server.codec.handler.OrderServerProcessHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/21 下午3:37
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boosGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("boosGroup"));
        EventLoopGroup workerGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("workerGroup"));
        final MetricHandler metricHandler = new MetricHandler();
        try {
            log.info("server starting");
            final ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class)
                    .group(boosGroup, workerGroup)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            final ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("orderFrameDecoder", new OrderFrameDecoder());
                            pipeline.addLast(new OrderFrameEncoder());
                            pipeline.addLast(new OrderProtocolEncoder());
                            pipeline.addLast(new OrderProtocolDecoder());
                            pipeline.addLast("metricHandler", metricHandler);
                            pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                            pipeline.addLast(new OrderServerProcessHandler());
                        }
                    });
            final ChannelFuture channelFuture = serverBootstrap.bind(8090).sync();
            final ChannelFuture sync = channelFuture.channel().closeFuture().sync();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
