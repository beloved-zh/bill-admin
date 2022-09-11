package com.beloved.common.enums;

import com.beloved.common.service.BaseEnum;
import lombok.Getter;

/**
 * @Author: Beloved
 * @CreateTime: 2022-09-11 17:04
 * @Description: 是否类型枚举
 */
@Getter
public enum WhetherEnum implements BaseEnum<Boolean, Integer> {

    YES(true, 1),
    NO(false, 0);

    private Boolean label;
    private Integer value;

    WhetherEnum(Boolean label, Integer value) {
        this.label = label;
        this.value = value;
    }
}
