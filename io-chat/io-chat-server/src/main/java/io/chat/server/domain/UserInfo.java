package io.chat.server.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 9:38 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user_info")
public class UserInfo {

    @TableId(type = IdType.ASSIGN_ID)
    private String userId;
    private String userNickName;
    private String userHead;


}
