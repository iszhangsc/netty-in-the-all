package io.netty.example.study.common.order;

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
 * @date 2020/4/21 下午2:41
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = true)
public class OrderOperation extends BaseOperation {

    private int tableId;
    private String dish;

    public OrderOperation(int tableId, String dish) {
        this.tableId = tableId;
        this.dish = dish;
    }

    @Override
    public OperationResult execute() {
        log.info("order's executing startup with orderResult:{}, {}", getDish(), getTableId());
        // 这里可以执行业务逻辑
        log.info("order's executing complete");
        return new OrderOperationResult(getTableId(), getDish(), true);
    }

}
