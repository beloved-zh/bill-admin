package com.beloved.common.utils;

import java.util.UUID;

/**
 * @author Beloved
 * @date 2020/7/10 16:35
 */
public class UUIDUtils {

    /**
     * 带-的UUID
     *
     * @return 36位的字符串
     */
    public static String getUUID2() {
        return UUID.randomUUID().toString();
    }

    /**
     * 去掉-的UUID
     *
     * @return 32位的字符串
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
