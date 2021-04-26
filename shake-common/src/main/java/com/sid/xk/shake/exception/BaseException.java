package com.sid.xk.shake.exception;

/**
 * @author wuxiaodong
 * @date 2021/04/03
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public static <T> T requireNonNull(T obj, String message) {
        if (obj == null) {
            BaseException.throwException(message);
        }
        return obj;
    }

    public static void throwException(String msg) {
        throw new BaseException(msg);
    }

    public BaseException(String msg) {
        super(msg);
    }

}
