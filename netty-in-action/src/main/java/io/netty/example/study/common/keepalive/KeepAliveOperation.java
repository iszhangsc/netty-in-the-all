package io.netty.example.study.common.keepalive;

import io.netty.example.study.common.BaseOperation;
import io.netty.example.study.common.OperationResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/21 下午2:38
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
public class KeepAliveOperation extends BaseOperation {

    private long time;

    public KeepAliveOperation() {
        this.time = System.nanoTime();
    }

    @Override
    public OperationResult execute() {
        return new KeepAliveOperationResult(time);
    }

}
