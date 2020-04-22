package io.netty.example.study.common.auth;

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
 * @date 2020/4/21 下午2:33
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
public class AuthOperation extends BaseOperation {

    private final String username;
    private final String password;

    @Override
    public OperationResult execute() {
        // 简单实现认证..
        if ("admin".equalsIgnoreCase(this.username)) {
            log.info("认证成功...........................");
            return new AuthOperationResult(true);
        }
        return new AuthOperationResult(false);
    }

}
