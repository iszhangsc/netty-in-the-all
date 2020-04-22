package io.netty.example.study.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.example.study.client.codec.OrderFrameDecoder;
import io.netty.example.study.client.codec.OrderFrameEncoder;
import io.netty.example.study.client.codec.OrderProtocolDecoder;
import io.netty.example.study.client.codec.OrderProtocolEncoder;
import io.netty.example.study.common.OperationResult;
import io.netty.example.study.common.RequestMessage;
import io.netty.example.study.common.order.OrderOperation;
import io.netty.example.study.server.codec.ResponseDispatcherHandler;
import io.netty.example.study.server.codec.dispatcher.OperationResultFuture;
import io.netty.example.study.server.codec.dispatcher.RequestPendingCenter;
import io.netty.example.study.util.IdUtil;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.ExecutionException;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/21 下午3:51
 */
public class ClientV2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Bootstrap bootstrap = new Bootstrap();
        // 连接超时10s
        bootstrap.option(NioChannelOption.CONNECT_TIMEOUT_MILLIS, 10 * 1000);
        final RequestPendingCenter requestPendingCenter = new RequestPendingCenter();
        bootstrap.channel(NioSocketChannel.class)
                .group(new NioEventLoopGroup())
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new OrderFrameDecoder());
                        pipeline.addLast(new OrderFrameEncoder());
                        pipeline.addLast(new OrderProtocolDecoder());
                        pipeline.addLast(new OrderProtocolEncoder());

                        pipeline.addLast(new ResponseDispatcherHandler(requestPendingCenter));

                        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                    }
                });
        final ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8090);
        channelFuture.sync();

        final long streamId = IdUtil.nextId();
        final OrderOperation operation = new OrderOperation(1001, "土豆");
        RequestMessage requestMessage = new RequestMessage(streamId, operation);

        OperationResultFuture operationResultFuture = new OperationResultFuture();
        requestPendingCenter.add(streamId, operationResultFuture);

        for (int i = 0; i < 20000; i++) {
            channelFuture.channel().writeAndFlush(requestMessage);
        }

        OperationResult result = operationResultFuture.get();
        System.out.println(result);
        channelFuture.channel().closeFuture().get();
    }

}
