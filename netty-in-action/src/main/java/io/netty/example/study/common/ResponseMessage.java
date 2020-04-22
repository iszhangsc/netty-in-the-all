package io.netty.example.study.common;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/21 下午3:04
 */
public class ResponseMessage extends BaseMessage<OperationResult> {

    @Override
    public Class getMessageBodyDecodeClass(int opCode) {
        return OperationType.fromOpCode(opCode).getOperationResultClazz();
    }


}
