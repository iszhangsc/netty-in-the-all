package io.netty.example.study.common.auth;

import io.netty.example.study.common.OperationResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/21 下午2:36
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthOperationResult extends OperationResult {

    private final boolean passAuth;

}
