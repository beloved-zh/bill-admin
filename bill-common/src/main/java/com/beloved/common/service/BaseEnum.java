package com.beloved.common.service;

import com.beloved.common.utils.ObjectUtils;

import java.util.EnumSet;
import java.util.Objects;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-19 13:58
 * @Description: 枚举通用接口
 */
public interface BaseEnum<T> {

    T getValue();

    String getLabel();

    static <E extends Enum<E> & BaseEnum> E getEnumByValue(Object value, Class<E> clazz) {
        Objects.requireNonNull(value);
        EnumSet<E> allEnums = EnumSet.allOf(clazz); // 获取类型下的所有枚举
        E matchEnum = allEnums.stream()
                .filter(e -> Objects.equals(e.getValue(), value))
                .findFirst()
                .orElse(null);
        return matchEnum;
    }
    
    static <E extends Enum<E> & BaseEnum> String getLabelByValue(Object value, Class<E> clazz) {
        Objects.requireNonNull(value);
        EnumSet<E> allEnums = EnumSet.allOf(clazz); // 获取类型下的所有枚举
        E matchEnum = allEnums.stream()
                .filter(e -> Objects.equals(e.getValue(), value))
                .findFirst()
                .orElse(null);
        
        if (ObjectUtils.isNotEmpty(matchEnum)) {
            return matchEnum.getLabel();
        }
        return null;
    }

    static <E extends Enum<E> & BaseEnum> Object getValueByLabel(String label, Class<E> clazz) {
        Objects.requireNonNull(label);
        EnumSet<E> allEnums = EnumSet.allOf(clazz); // 获取类型下的所有枚举
        E matchEnum = allEnums.stream()
                .filter(e -> Objects.equals(e.getLabel(), label))
                .findFirst()
                .orElse(null);

        if (ObjectUtils.isNotEmpty(matchEnum)) {
            return matchEnum.getValue();
        }
        return null;
    }
}
