package io.chat.agreement.protocol;

import io.chat.agreement.protocol.login.LoginRequest;
import io.chat.agreement.protocol.login.LoginResponse;

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
        PACKET_TYPE.put(Command.MSG_REQUEST, );
        PACKET_TYPE.put(Command.MSG_RESPONSE, );
        PACKET_TYPE.put(Command.TALK_NOTICE_REQUEST, );
        PACKET_TYPE.put(Command.TALK_NOTICE_RESPONSE, );
        PACKET_TYPE.put(Command.SEARCH_FRIEND_REQUEST, );
        PACKET_TYPE.put(Command.SEARCH_FRIEND_RESPONSE, );
        PACKET_TYPE.put(Command.Add_Friend_Request, );
        PACKET_TYPE.put(Command.ADD_FRIEND_RESPONSE, );
        PACKET_TYPE.put(Command.DEL_TALK_REQUEST, );
        PACKET_TYPE.put(Command.MSG_GROUP_REQUEST, );
        PACKET_TYPE.put(Command.MSG_GROUP_RESPONSE, );
        PACKET_TYPE.put(Command.RECONNECT_REQUEST, );
    }

    public Class<? extends  Packet> getClass(Byte command) {
        return PACKET_TYPE.get(command);
    }

    /**
     * 获取协议指令
     * @return  返回指令值
     */
    public abstract Byte getCommand();

}
