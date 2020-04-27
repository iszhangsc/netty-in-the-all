package io.chat.agreement.protocol.friend.dto;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 8:28 下午
 */
@Data
public class UserDto {

    private String userId;       // 用户ID
    private String userNickName; // 用户昵称
    private String userHead;     // 用户头像
    private Integer status;      // 状态；0添加、1[保留]、2已添加

}
