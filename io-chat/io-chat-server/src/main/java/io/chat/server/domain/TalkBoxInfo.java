package io.chat.server.domain;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 对话框信息
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 9:43 下午
 */
@Data
public class TalkBoxInfo {

    private Integer talkType; // 对话框类型；0好友、1群组
    private String talkId;    // 对话框ID(好友ID、群组ID)
    private String talkName;  // 对话框名称
    private String talkHead;  // 对话框头像
    private String talkSketch;  // 消息简述
    private Date talkDate;      // 消息时间

}
