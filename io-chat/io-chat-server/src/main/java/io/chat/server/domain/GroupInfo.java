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
 * @date 2020/4/27 10:08 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupInfo {

    private String groupId;
    private String groupName;
    private String groupHead;
}
