package io.chat.agreement.protocol.msg;

import io.chat.agreement.protocol.Command;
import io.chat.agreement.protocol.Packet;

import java.util.Date;

/**
 * <p>
 * 群消息应答
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 7:57 下午
 */
public class MsgGroupResponse extends Packet {


    private String talkId;
    private String userId;
    /**
     * 群成员用户昵称
     */
    private String userNickName;
    /**
     * 群成员头像
     */
    private String userHead;
    private String msg;
    /**
     * 消息类型： 0文字消息、1固定表情
     */
    private Integer msgType;
    private Date msgDate;


    @Override
    public Byte getCommand() {
        return Command.MSG_GROUP_RESPONSE;
    }

}
