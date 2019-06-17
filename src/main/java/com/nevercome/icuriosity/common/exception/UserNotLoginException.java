package com.nevercome.icuriosity.common.exception;

import com.nevercome.icuriosity.common.result.ResultCode;

/**
 * @author: sun
 * @date: 2019/6/17
 */
public class UserNotLoginException extends BusinessException {

    private static final long serialVersionUID = 3721036867889297081L;

    public UserNotLoginException() {
        super();
    }

    public UserNotLoginException(Object data) {
        super();
        super.data = data;
    }

    public UserNotLoginException(ResultCode resultCode) {
        super(resultCode);
    }

    public UserNotLoginException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public UserNotLoginException(String msg) {
        super(msg);
    }

    public UserNotLoginException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }

}
