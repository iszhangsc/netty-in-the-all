package io.chat.agreement.protocol.friend;

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
 * @date 2020/4/27 7:34 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AddFriendResponse extends Packet {

    private String friendId;
    private String friendNickName;
    private String friendHead;

    @Override
    public Byte getCommand() {
        return Command.ADD_FRIEND_RESPONSE;
    }
}
