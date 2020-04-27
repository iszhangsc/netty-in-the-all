package io.chat.server.infrastructure.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * <p>
 * 基础常量类
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 9:47 下午
 */
public class Constants {

    /**
     * 响应码
     */
    @Getter
    @AllArgsConstructor
    public enum ResponseCode {
        SUCCESS("0000", "成功"),
        UN_ERROR("0001", "未知失败"),
        ILLEGAL_PARAMETER("0002", "非法参数"),
        INDEX_DUP("0003", "主键冲突");
        private String code;
        private String info;
    }

    /**
     * 聊天类型
     */
    @Getter
    @AllArgsConstructor
    public enum TalkType {
        Friend(0, "好友"),
        Group(1, "群组");

        private Integer code;
        private String info;
    }

    @Getter
    @AllArgsConstructor
    public enum MsgType {

        Myself(0, "自己"),
        Friend(1, "好友");

        private Integer code;
        private String info;
    }

}
