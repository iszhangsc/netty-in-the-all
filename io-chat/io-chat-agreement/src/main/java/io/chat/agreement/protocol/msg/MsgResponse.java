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
 * 消息响应
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 7:25 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MsgResponse extends Packet {

    /**
     *  好友ID[对方]
     */
    private String friendId;
    /**
     * 传输消息内容
     */
    private String msgText;
    /**
     * 消息类型；0文字消息、1固定表情
     */
    private Integer msgType;
    /**
     * 消息时间
     */
    private Date msgDate;

    @Override
    public Byte getCommand() {
        return Command.MSG_RESPONSE;
    }

}
