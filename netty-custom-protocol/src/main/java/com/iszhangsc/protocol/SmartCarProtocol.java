package com.iszhangsc.protocol;

import com.iszhangsc.constant.ConstantValue;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author zhangshichang
 * @date 2020/4/3 下午4:45
 */
@Data
public class SmartCarProtocol {

    private int headData = ConstantValue.HEAD_DATA;

    private int contentLength;

    private byte[] content;

    public SmartCarProtocol(int contentLength, byte[] content) {
        this.contentLength = contentLength;
        this.content = content;
    }

}
