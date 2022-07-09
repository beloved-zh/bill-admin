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
    SUCCESS(200, "操作成功"),

    // =============== 4xx ===============
    BAD_REQUEST(400, "请求错误"),

    // =============== 5xx ===============
    ERROR(500, "服务器内部错误"),


    TEST(600, "测试");

    private Integer code;
    private String message;


    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
