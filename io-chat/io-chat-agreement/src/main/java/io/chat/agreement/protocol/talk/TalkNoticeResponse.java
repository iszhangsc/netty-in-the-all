package io.chat.agreement.protocol.talk;

import io.chat.agreement.protocol.Command;
import io.chat.agreement.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 * 对话通知应答
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 7:26 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TalkNoticeResponse extends Packet {

    /**
     * 对话框ID [好友、群ID]
     */
    private String talkId;
    /**
     * 对话框名称[好友、群名称]
     */
    private String talkName;
    /**
     * 对话框头像[好友、群头像]
     */
    private String talkHead;
    /**
     * 消息简讯
     */
    private String talkSketch;
    /**
     * 消息时间
     */
    private Date talkDate;

    @Override
    public Byte getCommand() {
        return Command.TALK_NOTICE_RESPONSE;
    }
}
