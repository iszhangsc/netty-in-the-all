package io.netty.example.study.server.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.example.study.common.ResponseMessage;
import io.netty.example.study.server.codec.dispatcher.RequestPendingCenter;

/**
 * <p>
 *  响应请求分发拦截器
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/22 上午11:26
 */
public class ResponseDispatcherHandler extends SimpleChannelInboundHandler<ResponseMessage> {

    private RequestPendingCenter requestPendingCenter;

    public ResponseDispatcherHandler(RequestPendingCenter requestPendingCenter) {
        this.requestPendingCenter = requestPendingCenter;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ResponseMessage msg) throws Exception {
        requestPendingCenter.set(msg.getMessageHeader().getStreamId(), msg.getMessageBody());
    }

}
