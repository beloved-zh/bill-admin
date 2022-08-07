package com.beloved.common.enums;

import com.beloved.common.service.StatusCode;
import lombok.Getter;

/**
 * 返回视图状态码枚举类
 *
 * @author beloved
 */
@Getter
public enum ResultCode implements StatusCode {
    SUCCESS(2000, "操作成功"),
    LOGOUT_SUCCESS(2001, "注销成功");

    private Integer code;
    private String message;


    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
