package io.netty.example.study.client.codec;

import io.netty.handler.codec.LengthFieldPrepender;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/21 下午3:23
 */
public class OrderFrameEncoder extends LengthFieldPrepender {

    public OrderFrameEncoder() {
        super(2);
    }

}
