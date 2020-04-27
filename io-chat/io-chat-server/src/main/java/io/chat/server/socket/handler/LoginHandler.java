package io.chat.server.socket.handler;

import com.alibaba.fastjson.JSON;
import io.chat.agreement.protocol.login.LoginRequest;
import io.chat.agreement.protocol.login.LoginResponse;
import io.chat.agreement.protocol.login.dto.ChatRecordDto;
import io.chat.agreement.protocol.login.dto.ChatTalkDto;
import io.chat.agreement.protocol.login.dto.GroupsDto;
import io.chat.agreement.protocol.login.dto.UserFriendDto;
import io.chat.server.domain.*;
import io.chat.server.infrastructure.common.Constants;
import io.chat.server.infrastructure.common.SocketChannelUtils;
import io.chat.server.service.UserService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  登录请求处理器
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 9:26 下午
 */
@Slf4j
public class LoginHandler extends BaseBizHandler<LoginRequest> {

    public LoginHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void channelRead(Channel channel, LoginRequest msg) {
        log.info("登录请求处理:{}", JSON.toJSON(msg));
        // 1. 登录失败返回
        String userId = msg.getUserId();
        boolean auth = userService.checkAuth(userId, msg.getUserPassword());
        if (!auth) {
            // 返回失败响应
            channel.writeAndFlush(new LoginResponse(false));
            return;
        }

        // 2. 登录成功绑定Channel
        // 2.1绑定用户ID
        SocketChannelUtils.addChannel(userId, channel);
        // 2.2绑定群组
        List<String> groupsIdList = userService.queryUserGroupsIdList(userId);
        for (String groupId : groupsIdList) {
            SocketChannelUtils.addChannelGroup(groupId, channel);
        }
        // 3.反馈消息：用户信息、用户对话框列表、好友列表、群组列表
        // 组装消息包
        LoginResponse response = new LoginResponse();
        UserInfo userInfo = userService.queryUserInfo(userId);
        List<TalkBoxInfo> talkBoxInfoList = userService.queryTalkBoxInfoList(userId);
        for (TalkBoxInfo talkBoxInfo : talkBoxInfoList) {
            ChatTalkDto chatTalk = new ChatTalkDto();
            chatTalk.setTalkId(talkBoxInfo.getTalkId());
            chatTalk.setTalkType(talkBoxInfo.getTalkType());
            chatTalk.setTalkName(talkBoxInfo.getTalkName());
            chatTalk.setTalkHead(talkBoxInfo.getTalkHead());
            chatTalk.setTalkSketch(talkBoxInfo.getTalkSketch());
            chatTalk.setTalkDate(talkBoxInfo.getTalkDate());
            response.getChatTalkList().add(chatTalk);

            // 好友：聊天消息;
            if (Constants.TalkType.Friend.getCode().equals(talkBoxInfo.getTalkType())) {
                List<ChatRecordDto> chatRecordDtoList = new ArrayList<>();
                List<ChatRecordInfo> chatRecordInfoList = userService.queryChatRecordInfoList(talkBoxInfo.getTalkId(), msg.getUserId(), Constants.TalkType.Group.getCode());
                for (ChatRecordInfo chatRecordInfo : chatRecordInfoList) {
                    ChatRecordDto chatRecordDto = new ChatRecordDto();
                    chatRecordDto.setTalkId(talkBoxInfo.getTalkId());
                    boolean msgType = userId.equals(chatRecordInfo.getUserId());
                    // 自己发的消息
                    if (msgType) {
                        chatRecordDto.setUserHead(chatRecordInfo.getUserId());
                        chatRecordDto.setMsgUserType(0);
                    }
                    // 好友发送的消息
                    else {
                        chatRecordDto.setUserHead(chatRecordInfo.getFriendId());
                        chatRecordDto.setMsgUserType(1);
                    }
                    chatRecordDto.setMsgContent(chatRecordInfo.getMsgContent());
                    chatRecordDto.setMsgType(chatRecordDto.getMsgType());
                    chatRecordDto.setMsgDate(chatRecordInfo.getMsgDate());
                    chatRecordDtoList.add(chatRecordDto);
                }
                chatTalk.setChatRecordList(chatRecordDtoList);
            }
            // 群组：聊天消息;
            else if (Constants.TalkType.Group.getCode().equals(talkBoxInfo.getTalkType())) {
                List<ChatRecordDto> chatRecordDtoList = new ArrayList<>();
                List<ChatRecordInfo> chatRecordInfoList = userService.queryChatRecordInfoList(talkBoxInfo.getTalkId(), msg.getUserId(), Constants.TalkType.Group.getCode());
                for (ChatRecordInfo chatRecordInfo : chatRecordInfoList) {
                    UserInfo memberInfo = userService.queryUserInfo(chatRecordInfo.getUserId());
                    ChatRecordDto chatRecordDto = new ChatRecordDto();
                    chatRecordDto.setTalkId(talkBoxInfo.getTalkId());
                    chatRecordDto.setUserId(memberInfo.getUserId());
                    chatRecordDto.setUserNickName(memberInfo.getUserNickName());
                    chatRecordDto.setUserHead(memberInfo.getUserHead());
                    chatRecordDto.setMsgContent(chatRecordInfo.getMsgContent());
                    chatRecordDto.setMsgDate(chatRecordInfo.getMsgDate());
                    boolean msgType = msg.getUserId().equals(chatRecordInfo.getUserId());
                    // 消息类型[0自己/1好友]
                    chatRecordDto.setMsgUserType(msgType ? 0 : 1);
                    chatRecordDto.setMsgType(chatRecordInfo.getMsgType());
                    chatRecordDtoList.add(chatRecordDto);
                }
                chatTalk.setChatRecordList(chatRecordDtoList);
            }
        }

        // 3.3 群组
        List<GroupInfo> groupInfos = userService.queryUserGroupInfoList(userId);
        for (GroupInfo groupsInfo : groupInfos) {
            GroupsDto groups = new GroupsDto();
            groups.setGroupId(groupsInfo.getGroupId());
            groups.setGroupName(groupsInfo.getGroupName());
            groups.setGroupHead(groupsInfo.getGroupHead());
            response.getGroupList().add(groups);
        }

        // 3.4 好友
        List<UserFriendInfo> userFriendInfoList = userService.queryUserFriendInfoList(msg.getUserId());
        for (UserFriendInfo userFriendInfo : userFriendInfoList) {
            UserFriendDto userFriend = new UserFriendDto();
            userFriend.setFriendId(userFriendInfo.getFriendId());
            userFriend.setFriendName(userFriendInfo.getFriendName());
            userFriend.setFriendHead(userFriendInfo.getFriendHead());
            response.getUserFriendList().add(userFriend);
        }
        response.setSuccess(true);
        response.setUserId(userInfo.getUserId());
        response.setUserNickName(userInfo.getUserNickName());
        response.setUserHead(userInfo.getUserHead());
        // 传输消息
        channel.writeAndFlush(response);
    }

}
