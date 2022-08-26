package com.beloved.common.enums;

import com.beloved.common.service.BaseEnum;
import lombok.Getter;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-19 13:59
 * @Description: 性别枚举
 */
@Getter
public enum GenderEnum implements BaseEnum<String> {

    MALE("男","0"),
    FEMALE("女", "1"),
    UNKNOWN("未知", "2");

    
    private String label;
    private String value;

    GenderEnum(String label, String value) {
        this.label = label;
        this.value = value;
    }
}
