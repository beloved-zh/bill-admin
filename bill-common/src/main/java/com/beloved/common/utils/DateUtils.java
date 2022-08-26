package com.beloved.common.utils;

import java.text.ParseException;
import java.util.Date;

/**
 * @Author: Beloved
 * @CreateTime: 2022-07-27 14:37
 * @Description: 时间处理
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public final static String YYYY_MM_DD = "yyyy-MM-dd";
    
    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 解析时间
     * @param str
     * @return
     */
    public static Date parseDate(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        try {
            return parseDate(str, parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }
}
