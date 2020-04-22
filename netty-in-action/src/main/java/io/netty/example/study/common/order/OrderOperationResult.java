package io.netty.example.study.common.order;

import io.netty.example.study.common.OperationResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/21 下午2:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderOperationResult extends OperationResult {

    private final Integer tableId;
    private final String dish;
    private final Boolean complete;

}
