package io.netty.example.study.common;

import io.netty.example.study.common.auth.AuthOperation;
import io.netty.example.study.common.auth.AuthOperationResult;
import io.netty.example.study.common.keepalive.KeepAliveOperation;
import io.netty.example.study.common.keepalive.KeepAliveOperationResult;
import io.netty.example.study.common.order.OrderOperation;
import io.netty.example.study.common.order.OrderOperationResult;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

/**
 * <p>
 *  操作类型枚举类
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/21 下午2:28
 */
@AllArgsConstructor
public enum OperationType {

    /**
     * 认证
     */
    AUTH(1, AuthOperation.class, AuthOperationResult.class),
    /**
     * 保持连接
     */
    KEEPALIVE(2, KeepAliveOperation.class, KeepAliveOperationResult.class),
    /**
     * 下单
     */
    ORDER(3, OrderOperation.class, OrderOperationResult.class);


    private final int opCode;
    private final Class<? extends BaseOperation> operationClazz;
    private final Class<? extends OperationResult> operationResultClazz;

    public int getOpCode() {
        return opCode;
    }

    public Class<? extends BaseOperation> getOperationClazz() {
        return operationClazz;
    }

    public Class<? extends OperationResult> getOperationResultClazz() {
        return operationResultClazz;
    }

    public static OperationType fromOperation(BaseOperation operation) {
        return Stream.of(values()).filter(obj -> obj.getOperationClazz().equals(operation.getClass())).findAny()
                .orElseThrow(() -> new RuntimeException(operation + "不存在"));
    }

    public static OperationType fromOpCode(int opCode) {
        return Stream.of(values())
                .filter(obj -> obj.opCode == opCode)
                .findAny()
                .orElseThrow(() -> new RuntimeException(opCode + "不存在"));
    }

}
