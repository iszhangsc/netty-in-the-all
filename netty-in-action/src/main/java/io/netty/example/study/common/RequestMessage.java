package io.netty.example.study.common;

import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/21 下午2:43
 */
@NoArgsConstructor
public class RequestMessage extends BaseMessage<BaseOperation> {

    @Override
    public Class getMessageBodyDecodeClass(int opCode) {
        return OperationType.fromOpCode(opCode).getOperationClazz();
    }

    /**
     * 封装请求消息体
     * @param streamId
     * @param operation
     */
    public RequestMessage(Long streamId, BaseOperation operation) {
        MessageHeader messageHeader = new MessageHeader();
        messageHeader.setStreamId(streamId);
        messageHeader.setOpCode(OperationType.fromOperation(operation).getOpCode());
        this.setMessageHeader(messageHeader);
        this.setMessageBody(operation);
    }

}
