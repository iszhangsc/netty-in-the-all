package io.netty.example.study.server.codec.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.example.study.common.BaseOperation;
import io.netty.example.study.common.OperationResult;
import io.netty.example.study.common.RequestMessage;
import io.netty.example.study.common.ResponseMessage;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/21 下午3:27
 */
public class OrderServerProcessHandler extends SimpleChannelInboundHandler<RequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RequestMessage msg) throws Exception {

        final BaseOperation operation = msg.getMessageBody();
        final OperationResult execute = operation.execute();


        final ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessageHeader(msg.getMessageHeader());
        responseMessage.setMessageBody(execute);

        ctx.writeAndFlush(responseMessage);
    }

}
