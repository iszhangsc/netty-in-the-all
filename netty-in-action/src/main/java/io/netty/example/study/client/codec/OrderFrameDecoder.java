package io.netty.example.study.client.codec;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/21 下午3:23
 */
public class OrderFrameDecoder extends LengthFieldBasedFrameDecoder {

    public OrderFrameDecoder() {
        super(Integer.MAX_VALUE, 0, 2, 0, 2);
    }

}
