package io.chat.server.service;

import io.chat.server.domain.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 8:52 下午
 */
public interface UserService {

    boolean checkAuth(String userId, String userPssword);

    List<String> queryUserGroupsIdList(String userId);

    UserInfo queryUserInfo(String userId);

    List<TalkBoxInfo> queryTalkBoxInfoList(String userId);

    List<ChatRecordInfo> queryChatRecordInfoList(String talkId, String userId, Integer code);

    List<GroupInfo> queryUserGroupInfoList(String userId);

    List<UserFriendInfo> queryUserFriendInfoList(String userId);
}
