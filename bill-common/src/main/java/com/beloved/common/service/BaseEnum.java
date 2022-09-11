package com.beloved.common.service;

import com.beloved.common.utils.ObjectUtils;

import java.util.Arrays;
import java.util.Objects;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-19 13:58
 * @Description: 枚举通用接口
 */
public interface BaseEnum<K, V> {

    K getLabel();
    
    V getValue();
    
    static <E extends Enum<E> & BaseEnum> E getEnumByValue(Object value, Class<E> enumClassType) {
        if (ObjectUtils.isEmpty(value)) return null;
        E matchEnum = Arrays.stream(enumClassType.getEnumConstants())
                .filter(e -> Objects.equals(e.getValue(), value))
                .findFirst()
                .orElse(null);
        return matchEnum;
    }

    static <E extends Enum<E> & BaseEnum> E getEnumByLabel(Object label, Class<E> enumClassType) {
        if (ObjectUtils.isEmpty(label)) return null;
        E matchEnum = Arrays.stream(enumClassType.getEnumConstants())
                .filter(e -> Objects.equals(e.getLabel(), label))
                .findFirst()
                .orElse(null);
        return matchEnum;
    }
}
