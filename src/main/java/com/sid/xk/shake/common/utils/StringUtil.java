package com.sid.xk.shake.common.utils;

import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 字符串工具类
 * @author wuxiaodong
 * @date 2021/04/03
 */
public class StringUtil extends StringUtils {

    public static final String BASE_RANDOM_FULL_CHAR_NUMBER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * 单个非空
     * @param str 校验字符串
     * @return boolean
     */
    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

    /**
     * 全部非空
     * @param args 校验字符串
     * @return boolean
     */
    public static boolean isNotEmpty(Object... args) {
        return !isOneEmpty(args);
    }

    /**
     * 任意为空
     * @param args 校验字符串
     * @return boolean
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
     * @param str 校验字符串
     * @param msg 为空返回信息,
     * @return String
     */
    public static String emptyToMsg(Object str, String msg) {
        String ret = "";
        if (isEmpty(str)) {
            ret = msg + ",";
        }
        return ret;
    }

    /**
     * MD5加密(大写)
     * @param s 加密字符串
     * @param charset 字符集
     * @return String
     */
    public static String getMd5(String s, String charset) throws UnsupportedEncodingException {
        return null == s ? null : getMd5(null == charset? s.getBytes() : s.getBytes(charset));
    }

    /**
     * MD5加密(大写)
     * @param bytes 加密字节
     * @return String
     */
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

    /**
     * 左填充
     * @param source 原字符串
     * @param fill 填充字符
     * @param length 填充后长度
     * @return String
     */
    public static String fillLeft(String source, char fill, int length) {
        StringBuffer ret = new StringBuffer();

        if (source.length() > length) {
            return source;
        } else {
            int fillLength = length - source.length();
            ret.append(String.valueOf(fill).repeat(fillLength)).append(source);
        }
        return ret.toString();
    }

    /**
     * 全部相同
     * @param source 原字符串
     * @param args 比较字符串
     * @return boolean
     */
    public static boolean equals(Object source, Object... args) {
        for (Object arg : args) {
            if (!Objects.equals(source, arg)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取随机字符
     * @param length 长度
     * @return String
     */
    public static String randomString(int length) {
        return randomString(BASE_RANDOM_FULL_CHAR_NUMBER, length);
    }

    /**
     * 获取随机字符, 指定字符范围
     * @param baseString 字符串
     * @param length 长度
     * @return String
     */
    public static String randomString(String baseString, int length) {
        if (isEmpty(baseString)) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder(length);
            if (length < 1) {
                length = 1;
            }
            for(int i = 0; i < length; ++i) {
                int number = ThreadLocalRandom.current().nextInt(baseString.length());
                sb.append(baseString.charAt(number));
            }
            return sb.toString();
        }
    }

}

