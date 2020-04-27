package io.chat.agreement.protocol.login;

import io.chat.agreement.protocol.Command;
import io.chat.agreement.protocol.Packet;
import io.chat.agreement.protocol.login.dto.ChatTalkDto;
import io.chat.agreement.protocol.login.dto.GroupsDto;
import io.chat.agreement.protocol.login.dto.UserFriendDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 下午6:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LoginResponse extends Packet {

    private boolean success;
    private String userId;
    private String userHead;
    private String userNickName;
    private List<ChatTalkDto> chatTalkList = new ArrayList<>();
    private List<GroupsDto> groupList = new ArrayList<>();
    private List<UserFriendDto> userFriendList = new ArrayList<>();

    public LoginResponse(boolean success) {
        this.success = success;
    }

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

}
