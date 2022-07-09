package com.beloved.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanConvertUtils {

    /**
     * 普通转换
     * @param source 源
     * @param targetClass
     * @param <T>
     * @return
     */
    public static  <T> T  copy(Object source,Class<T> targetClass){
        return copy(source ,targetClass,null);
    }

    /**
     * 普通转换
     * @param sourceObj 源
     * @param targetObj 目标
     * @param <T>
     * @return
     */
    public static  <T> T  copy(Object sourceObj,T targetObj){
        return copy(sourceObj ,targetObj,null);
    }

    /**
     * 普通转换
     * @param source 源
     * @param targetClass
     * @param keyMap  源字段=目标字段
     * @return
     */
    public static  <T> T  copy(Object source,Class<T> targetClass,String ...keyMap){
        T targetObj= null;
        try {
            targetObj = targetClass.getConstructor(null).newInstance(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return copy(source,targetObj,keyMap);
    }

    /**
     * 普通转换
     * @param sourceObj 源
     * @param targetObj 目标
     * @param keyMap  源字段=目标字段
     * @return
     */
    public static  <T> T  copy(Object sourceObj,T targetObj,String ...keyMap){
        Class<?> sourceClass = sourceObj.getClass();
        Class<?> targetClass = targetObj.getClass();
        Map<String, String> keyMaps = new HashMap<>();
        if (keyMap!=null&&keyMap.length>0){
            for (String keyItem : keyMap) {
                String[] split = keyItem.split("=");
                keyMaps.put(split[0],split[1]);
            }
        }
        Field[] sourceFieldFields = sourceObj.getClass().getDeclaredFields();
        for (Field sourceField : sourceFieldFields) {
            String sourceFieldName = sourceField.getName();
            String targetFieldName=keyMaps.getOrDefault(sourceFieldName,sourceFieldName);
            String sourceMethodName = getGetMethodName(sourceFieldName);
            String targetMethodName = getSetMethodName(targetFieldName);
            try {
                Method sourceGetMethod = sourceClass.getDeclaredMethod(sourceMethodName);
                Method targetSetField = targetClass.getDeclaredMethod(targetMethodName,sourceField.getType());
                Object value = sourceGetMethod.invoke(sourceObj);
                targetSetField.invoke(targetObj,value);
            } catch (Exception e) {
                //找不到字段
            }
        }
        return targetObj;
    }

    /**
     * 首字母大写
     * @param str
     * @return
     */
    private static String strFirstUpper(String str){
        return str.substring(0,1).toUpperCase()+str.substring(1);
    }

    /**
     * 获取get方法名
     * @param fieldName
     * @return
     */
    private static String getGetMethodName(String fieldName){
        return "get"+strFirstUpper(fieldName);
    }

    private static String getSetMethodName(String fieldName){
        return "set"+strFirstUpper(fieldName);
    }
}
