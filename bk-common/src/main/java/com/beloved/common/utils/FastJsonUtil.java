package com.beloved.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FastJson 工具类
 *
 * @author beloved
 */
public class FastJsonUtil {

    /**
     * 对象转换成json字符串
     *
     * @param obj 需要转换的对象
     * @return 转换后 JSON 字符串
     */
    public static String objectToJson(Object obj){
        return JSON.toJSONString(obj);
    }

    /**
     * JSON 字符串转 Map 集合
     *
     * @param jsonStr JSON 字符串
     * @return  转换后的 Map
     */
    public static HashMap jsonToMap(String jsonStr){
        return JSON.parseObject(jsonStr, HashMap.class);
    }

    /**
     * JSON 字符串转换成 List 集合
     *
     * @param jsonString 需要转换的对象
     * @param cla        目标实体
     * @return           转换后的集合
     */
    public static <T> List<T> json2List(String jsonString, Class cla){
        try {
            return JSON.parseArray(jsonString, cla);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * JSON 字符串转换成对象
     *
     * @param jsonString 需要转换的 JSON
     * @param cla 目标对象
     * @return 转换对象
     */
    public static <T> T jsonToBean(String jsonString, Class<T> cla){
        try {
            return JSON.parseObject(jsonString, cla);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
