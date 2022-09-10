package com.beloved.common.enums;

import com.beloved.common.service.BaseEnum;
import lombok.Getter;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-08 16:18
 * @Description: 状态枚举
 */
@Getter
public enum StatusEnum implements BaseEnum<Integer> {

    NORMAL("正常", 1),
    STOP("停用", 0);
    
    private String label;
    private Integer value;

    StatusEnum(String label, Integer value) {
        this.label = label;
        this.value = value;
    }
}
