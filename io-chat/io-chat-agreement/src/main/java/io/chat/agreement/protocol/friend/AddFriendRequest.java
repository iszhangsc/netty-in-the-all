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
 * @date 2020/4/27 7:29 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AddFriendRequest extends Packet {

    private String userId;    // 用户ID[自己的ID]
    private String friendId;  // 好友

    @Override
    public Byte getCommand() {
        return Command.Add_Friend_Request;
    }
}
