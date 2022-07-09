package com.beloved.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 *
 * @author beloved
 */
public class RegexUtils {

    /**
     * 校验正则
     * @param str       校验值
     * @param regex     校验正则
     * @return          校验结果
     */
    public static boolean check(String str, String regex) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(regex)) {
            return false;
        }
        return  Pattern.compile(regex).matcher(str).matches();
    }

}
