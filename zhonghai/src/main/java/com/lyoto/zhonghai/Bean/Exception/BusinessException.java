package com.lyoto.zhonghai.Bean.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Justmemoryl   Email: justmemoryl@foxmail.com
 * @version 1.0 created at 2019/05/08 09:48
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    private static final long   serialVersionUID = -1L;
    private              String code;
    private              String info;

    public BusinessException(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public BusinessException(String code, String info, Throwable e) {
        super(info, e);
        this.code = code;
        this.info = info;
    }
}
