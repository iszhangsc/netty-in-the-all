package io.chat.agreement.protocol;

import io.chat.agreement.protocol.friend.AddFriendRequest;
import io.chat.agreement.protocol.friend.AddFriendResponse;
import io.chat.agreement.protocol.friend.SearchFriendRequest;
import io.chat.agreement.protocol.friend.SearchFriendResponse;
import io.chat.agreement.protocol.login.LoginRequest;
import io.chat.agreement.protocol.login.LoginResponse;
import io.chat.agreement.protocol.login.ReConnectRequest;
import io.chat.agreement.protocol.msg.MsgGroupRequest;
import io.chat.agreement.protocol.msg.MsgGroupResponse;
import io.chat.agreement.protocol.msg.MsgRequest;
import io.chat.agreement.protocol.msg.MsgResponse;
import io.chat.agreement.protocol.talk.DelTalkRequest;
import io.chat.agreement.protocol.talk.TalkNoticeRequest;
import io.chat.agreement.protocol.talk.TalkNoticeResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *  协议包
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 下午5:46
 */
public abstract class Packet {

    private final static Map<Byte, Class<? extends Packet>> PACKET_TYPE = new ConcurrentHashMap<>();

    static {
        PACKET_TYPE.put(Command.LOGIN_REQUEST, LoginRequest.class);
        PACKET_TYPE.put(Command.LOGIN_RESPONSE, LoginResponse.class);
        PACKET_TYPE.put(Command.MSG_REQUEST, MsgRequest.class);
        PACKET_TYPE.put(Command.MSG_RESPONSE, MsgResponse.class);
        PACKET_TYPE.put(Command.TALK_NOTICE_REQUEST, TalkNoticeRequest.class);
        PACKET_TYPE.put(Command.TALK_NOTICE_RESPONSE, TalkNoticeResponse.class);
        PACKET_TYPE.put(Command.SEARCH_FRIEND_REQUEST, SearchFriendRequest.class);
        PACKET_TYPE.put(Command.SEARCH_FRIEND_RESPONSE, SearchFriendResponse.class);
        PACKET_TYPE.put(Command.Add_Friend_Request, AddFriendRequest.class);
        PACKET_TYPE.put(Command.ADD_FRIEND_RESPONSE, AddFriendResponse.class);
        PACKET_TYPE.put(Command.DEL_TALK_REQUEST, DelTalkRequest.class);
        PACKET_TYPE.put(Command.MSG_GROUP_REQUEST, MsgGroupRequest.class);
        PACKET_TYPE.put(Command.MSG_GROUP_RESPONSE, MsgGroupResponse.class);
        PACKET_TYPE.put(Command.RECONNECT_REQUEST, ReConnectRequest.class);
    }

    public static Class<? extends  Packet> getClass(Byte command) {
        return PACKET_TYPE.get(command);
    }

    /**
     * 获取协议指令
     * @return  返回指令值
     */
    public abstract Byte getCommand();

}
