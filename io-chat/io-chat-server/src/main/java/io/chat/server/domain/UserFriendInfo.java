package io.chat.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 10:09 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFriendInfo {

    private String friendId;
    private String friendName;
    private String friendHead;
}
