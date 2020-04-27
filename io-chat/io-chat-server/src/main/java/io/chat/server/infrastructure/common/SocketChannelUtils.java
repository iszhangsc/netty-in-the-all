package io.chat.server.infrastructure.common;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * SocketChannel工具类
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 9:05 下午
 */
public final class SocketChannelUtils {

    /**
     * 用户Map
     */
    private static final Map<String, Channel> USER_CHANNEL = new ConcurrentHashMap<>();
    private static final Map<String, String> USER_CHANNEL_ID = new ConcurrentHashMap<>();


    /**
     * 群组Map
     */
    private static final Map<String, ChannelGroup> CHANNEL_GROUP_MAP = new ConcurrentHashMap<>();

    /**
     * 获取群组通信通道
     * @param talkId    对话框ID[群号]
     * @return  群组
     */
    public static ChannelGroup getChannelGroup(String talkId) {
        return CHANNEL_GROUP_MAP.get(talkId);
    }


    // 个人相关

    public static void addChannel(String userId, Channel channel) {
        USER_CHANNEL.put(userId, channel);
        USER_CHANNEL_ID.put(channel.id().toString(), userId);
    }

    public static void removeChannel(String channelId) {
        String userId = USER_CHANNEL_ID.get(channelId);
        if (userId == null) {
            return;
        }
        USER_CHANNEL.remove(userId);
    }

    public static void removeUserChannelByUserId(String userId) {
        USER_CHANNEL.remove(userId);
    }

    public static Channel getChannel(String userId) {
        return USER_CHANNEL.get(userId);
    }



    // 群组相关

    public synchronized static void addChannelGroup(String talkId, Channel userChannel) {
        ChannelGroup channelGroup = CHANNEL_GROUP_MAP.get(talkId);
        if (channelGroup == null) {
            // 初次可能没有 ChannelGroup
            channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
            CHANNEL_GROUP_MAP.put(talkId, channelGroup);
        }
        channelGroup.add(userChannel);
    }

    public synchronized static void removeChannelGroup(String talkId, Channel userChannel) {
        ChannelGroup channelGroup = CHANNEL_GROUP_MAP.get(talkId);
        if (channelGroup == null) {
            return;
        }
        channelGroup.remove(userChannel);
    }

    public static void removeChannelGroupByChannel(Channel channel) {
        for (ChannelGroup next : CHANNEL_GROUP_MAP.values()) {
            next.remove(channel);
        }
    }



}
