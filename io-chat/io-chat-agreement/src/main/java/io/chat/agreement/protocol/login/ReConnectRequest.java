package io.chat.agreement.protocol.login;

import io.chat.agreement.protocol.Command;
import io.chat.agreement.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *  重连请求
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 7:58 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReConnectRequest extends Packet {

    private String userId;

    @Override
    public Byte getCommand() {
        return Command.RECONNECT_REQUEST;
    }

}
