package com.beloved.common.enums;

import com.beloved.common.service.BaseEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-19 13:59
 * @Description: 性别枚举
 */
@Getter
public enum GenderEnum implements BaseEnum<String, Integer> {

    MALE("男",0),
    FEMALE("女", 1),
    UNKNOWN("未知", 2);

    @JsonValue
    private String label;
    private Integer value;

    GenderEnum(String label, Integer value) {
        this.label = label;
        this.value = value;
    }
}
