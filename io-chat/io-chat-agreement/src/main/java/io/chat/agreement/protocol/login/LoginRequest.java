package io.chat.agreement.protocol.login;

import io.chat.agreement.protocol.Command;
import io.chat.agreement.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 下午5:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LoginRequest extends Packet {

    private String userId;
    private String userPassword;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }


}
