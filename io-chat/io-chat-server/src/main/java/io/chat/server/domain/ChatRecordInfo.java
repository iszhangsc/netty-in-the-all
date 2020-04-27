package io.chat.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 9:54 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRecordInfo {

    private String userId;
    private String friendId;
    private String msgContent;
    private Integer msgType;
    private Date msgDate;
}
