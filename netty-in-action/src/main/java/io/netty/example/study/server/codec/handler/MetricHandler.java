package io.netty.example.study.server.codec.handler;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * 监控 可共享.
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/22 下午3:44
 */
@ChannelHandler.Sharable
public class MetricHandler extends ChannelDuplexHandler {

    private final AtomicLong total = new AtomicLong();

    {
        final MetricRegistry metricRegistry = new MetricRegistry();
        metricRegistry.register("total", (Gauge<Long>) total::longValue);

        final ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry).build();
        reporter.start(5, TimeUnit.SECONDS);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 加入连接 +1
        total.incrementAndGet();
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 释放连接 -1
        total.decrementAndGet();
        super.channelInactive(ctx);
    }

}
