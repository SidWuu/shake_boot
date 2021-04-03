package com.sid.xk.shake.common.exception;

/**
 * @author wuxiaodong
 * @date 2021/04/03
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public static void throwException(String msg) {
        throw new BaseException(msg);
    }

    public BaseException(String msg) {
        super(msg);
    }

}
