package com.sid.xk.shake.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间格式化工具
 * @author wuxiaodong
 * @date 2021/04/05
 */
public class DateUtil {

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATETIME_HOUR_FORMAT = "yyyy-MM-dd HH";

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_MONTH_FORMAT = "yyyy-MM";
    public static final String DATE_YEAR_FORMAT = "yyyy";

    public static final String TIME_HOUR_FORMAT = "HH";
    public static final String TIME_MINUTE_FORMAT = "mm";
    public static final String TIME_SECOND_FORMAT = "ss";

    public static String getNowStr(String pattern) {
        return getDateTimeStr(LocalDateTime.now(), pattern);
    }

    public static String getDateTimeStr(String datetime, String pattern) {
        return getDateTimeStr(LocalDateTime.parse(datetime), pattern);
    }

    public static String getDateTimeStr(LocalDateTime datetime, String pattern) {
        return datetime.format(DateTimeFormatter.ofPattern(pattern));
    }

}
