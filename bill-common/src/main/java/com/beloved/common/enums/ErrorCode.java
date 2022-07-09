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
    
    DEFAULT(500, "服务器内部错误"),
    
    UNAUTHORIZED(401, "认证失败，无法访问系统资源"),
    METHOD_NOT_ALLOWED(405, "请求方法不合法"),

    AUTH_FAIL(8001, "用户不存在/密码错误"),
    ;
    
    private Integer code;
    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
