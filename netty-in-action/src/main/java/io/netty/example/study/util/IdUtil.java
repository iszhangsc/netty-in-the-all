package io.netty.example.study.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/21 下午3:20
 */
public class IdUtil {

    private static final AtomicLong IDX = new AtomicLong();

    private IdUtil() {

    }

    public static long nextId() {
        return IDX.incrementAndGet();
    }

}
