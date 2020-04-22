package io.netty.example.study.server.codec.dispatcher;

import io.netty.example.study.common.OperationResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/22 上午11:21
 */
public class RequestPendingCenter {

    private final Map<Long, OperationResultFuture> map = new ConcurrentHashMap<>();

    public void add(Long streamId, OperationResultFuture future) {
        this.map.put(streamId, future);
    }

    public void set(Long streamId, OperationResult result) {
        final OperationResultFuture operationResultFuture = this.map.get(streamId);
        if (operationResultFuture != null) {
            operationResultFuture.setSuccess(result);
            // 避免Map 无限增加...
            this.map.remove(streamId);
        }
    }

}
