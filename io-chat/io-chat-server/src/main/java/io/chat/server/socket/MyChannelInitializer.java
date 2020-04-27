package io.chat.server.socket;

import io.chat.agreement.codec.ObjDecoder;
import io.chat.agreement.codec.ObjEncoder;
import io.chat.server.service.UserService;
import io.chat.server.socket.handler.LoginHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 8:53 下午
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    private UserService userService;

    public MyChannelInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 对象传输处理【解码】
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new ObjDecoder());
        // 各个业务拦截器
        pipeline.addLast(new LoginHandler(userService));

        // 对象传输处理【编码】
        pipeline.addLast(new ObjEncoder());
    }

}
