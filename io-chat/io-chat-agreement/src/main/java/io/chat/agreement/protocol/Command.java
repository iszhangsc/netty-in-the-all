package io.chat.agreement.protocol;

/**
 * <p>
 *  命令指令常量类
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 下午5:48
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;

    Byte MSG_REQUEST = 3;
    Byte MSG_RESPONSE = 4;

    Byte TALK_NOTICE_REQUEST = 5;
    Byte TALK_NOTICE_RESPONSE = 6;

    Byte SEARCH_FRIEND_REQUEST = 7;
    Byte SEARCH_FRIEND_RESPONSE = 8;

    Byte Add_Friend_Request = 9;
    Byte ADD_FRIEND_RESPONSE = 10;

    /**
     * 删除对话框 请求
     */
    Byte DEL_TALK_REQUEST = 11;

    Byte MSG_GROUP_REQUEST = 12;
    Byte MSG_GROUP_RESPONSE = 13;

    Byte RECONNECT_REQUEST = 14;

}
