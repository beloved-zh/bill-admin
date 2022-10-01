package com.beloved.common.enums;

import com.beloved.common.service.BaseEnum;
import lombok.Getter;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-11 17:04
 * @Description: 是否类型枚举
 */
@Getter
public enum BooleanEnum implements BaseEnum<Boolean, Integer> {

    TRUE(true, 1),
    FALSE(false, 0);
    
    private Boolean label;
    private Integer value;

    BooleanEnum(Boolean label, Integer value) {
        this.label = label;
        this.value = value;
    }
}
