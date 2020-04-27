package io.chat.agreement.protocol.talk;

import io.chat.agreement.protocol.Command;
import io.chat.agreement.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 删除对话请求
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/27 7:56 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DelTalkRequest extends Packet {

    private String userId;
    private String talkId;

    @Override
    public Byte getCommand() {
        return Command.DEL_TALK_REQUEST;
    }

}
