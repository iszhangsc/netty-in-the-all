package io.netty.example.study.common.keepalive;

import io.netty.example.study.common.OperationResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/21 下午2:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KeepAliveOperationResult extends OperationResult {

    private final long time;

}
