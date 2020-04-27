package io.chat.agreement.protocol.login.dto;

import lombok.Data;

/**
 * <p>
 *  用户好友dto
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 8:26 下午
 */
@Data
public class UserFriendDto {

    private String friendId;    // 好友ID
    private String friendName;  // 好友名称
    private String friendHead;  // 好友头像

}
