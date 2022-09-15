package com.beloved.common.utils;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-15 13:44
 * @Description:
 */
public class LocalDateTimeUtils extends LocalDateTimeUtil {

    /** 默认日期时间格式 */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /** 默认日期格式 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /** 默认时间格式 */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    
    public static String format(LocalDateTime dateTime) {
        return format(dateTime, DEFAULT_DATE_TIME_FORMAT);
    }

    public static String format(LocalDate date) {
        return format(date, DEFAULT_DATE_FORMAT);
    }
    
}
