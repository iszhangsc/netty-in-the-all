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
 * @date 2020/4/27 7:28 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SearchFriendRequest extends Packet {

    private String userId;     // 用户ID
    private String searchKey;  // 搜索字段


    @Override
    public Byte getCommand() {
        return Command.SEARCH_FRIEND_REQUEST;
    }
}
