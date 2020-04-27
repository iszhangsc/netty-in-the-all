package io.chat.agreement.protocol.msg;

import io.chat.agreement.protocol.Command;
import io.chat.agreement.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 * 群消息请求
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 7:57 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MsgGroupRequest extends Packet {

    private String talkId;
    private String userId;
    private String msgText;
    /**
     * 消息类型： 0文字消息、1固定表情
     */
    private Integer msgType;
    private Date msgDate;

    @Override
    public Byte getCommand() {
        return Command.MSG_GROUP_REQUEST;
    }
}
