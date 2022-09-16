package com.beloved.common.enums;

import com.beloved.common.service.BaseEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @Author: Beloved
 * @CreateTime: 2022-08-28 16:39
 * @Description: 菜单类型
 */
@Getter
public enum MenuTypeEnum implements BaseEnum<String, String> {

    DIR("目录", "D"),
    MENU("菜单", "M"),
    BUTTON("按钮", "B");
    
    @JsonValue
    private String label;
    private String value;

    MenuTypeEnum(String label, String value) {
        this.label = label;
        this.value = value;
    }
    
}
