package io.netty.example.study.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/21 下午2:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageHeader {

    private int version = 1;

    private long streamId;

    private int opCode;
}
