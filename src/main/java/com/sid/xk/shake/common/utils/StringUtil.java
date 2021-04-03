package com.sid.xk.shake.common.utils;

import org.springframework.util.StringUtils;

import java.security.MessageDigest;

/**
 * 字符串工具类
 * @author wuxiaodong
 * @date 2021/04/03
 */
public class StringUtil extends StringUtils {


    /**
     * 单个非空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

    /**
     * 全部非空
     * @param args
     * @return
     */
    public static boolean isNotEmpty(Object... args) {
        return !isOneEmpty(args);
    }

    /**
     * 只要存在空
     * @param args
     * @return
     */
    public static boolean isOneEmpty(Object... args) {
        boolean ret = false;
        for (Object arg : args) {
            if (isEmpty(arg)) {
                ret = true;
            }
        }
        return ret;
    }

    /**
     * 为空返回提示
     * @param str
     * @param msg
     * @return
     */
    public static String emptyToMsg(String str, String msg) {
        String ret = "";
        if (isEmpty(str)) {
            ret = msg + ",";
        }
        return ret;
    }

    /**
     * MD5加密(大写)
     * @param s
     * @return
     */
    public static String getMd5(String s) {
        return null == s ? null : getMd5(s.getBytes());
    }

    public static String getMd5(byte[] bytes) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            char[] str = new char[digest.length * 2];
            int i = 0;
            for (byte b : digest) {
                str[i++] = hexDigits[b >>> 4 & 15];
                str[i++] = hexDigits[b & 15];
            }
            return new String(str);
        } catch (Exception var9) {
            return null;
        }
    }

}
