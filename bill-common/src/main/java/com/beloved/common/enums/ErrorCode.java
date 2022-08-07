package com.beloved.common.enums;

import com.beloved.common.service.StatusCode;
import lombok.Getter;

/**
 * @Author: Beloved
 * @CreateTime: 2022-07-09 18:39
 * @Description: 自定义异常状态枚举
 */
@Getter
public enum ErrorCode implements StatusCode {
    
    DEFAULT(1000, "服务器内部错误"),
    UNAUTHORIZED(1001, "尚未认证，无法访问系统资源"),
    FORBIDDEN(1002, "权限不足无法访问"),
    METHOD_NOT_ALLOWED(1003, "请求方法不合法"),
    AUTH_FAIL(1004, "用户名或者密码错误"),
    CAPTCHA_EXPIRED(1005, "验证码已过期"),
    CAPTCHA_FAIL(1006, "验证失败"),
    GET_USER_FAIL(1007, "获取用户信息失败"),
    ;
    
    private Integer code;
    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
