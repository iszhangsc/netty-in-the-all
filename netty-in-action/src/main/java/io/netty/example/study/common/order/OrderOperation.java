package io.netty.example.study.common.order;

import com.google.common.util.concurrent.Uninterruptibles;
import io.netty.example.study.common.BaseOperation;
import io.netty.example.study.common.OperationResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

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
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        log.info("order's executing complete");
        return new OrderOperationResult(getTableId(), getDish(), true);
    }

}
