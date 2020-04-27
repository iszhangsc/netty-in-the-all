package io.chat.agreement.protocol.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  对话框dto
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 8:24 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatTalkDto {

    private String talkId;      // 对话框ID
    private Integer talkType;   // 对话框类型；0好友、1群组
    private String talkName;    // 用户昵称
    private String talkHead;    // 用户头像
    private String talkSketch;  // 消息简述
    private Date talkDate;      // 消息时间

    private List<ChatRecordDto> chatRecordList;  // 聊天记录
}
