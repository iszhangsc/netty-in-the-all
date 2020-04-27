package io.chat.agreement.protocol.talk;

import io.chat.agreement.protocol.Command;
import io.chat.agreement.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *  对话通知请求
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 7:26 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TalkNoticeRequest extends Packet {

    private String userId;
    private String friendUserId;
    /**
     * 对话框类型[0好友、1群组]
     */
    private Integer talkType;

    @Override
    public Byte getCommand() {
        return Command.TALK_NOTICE_REQUEST;
    }

}
