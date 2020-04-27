package io.chat.agreement.protocol.friend;

import io.chat.agreement.protocol.Command;
import io.chat.agreement.protocol.Packet;
import io.chat.agreement.protocol.friend.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 7:28 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SearchFriendResponse extends Packet {

    private List<UserDto> list;


    @Override
    public Byte getCommand() {
        return Command.SEARCH_FRIEND_RESPONSE;
    }
}
