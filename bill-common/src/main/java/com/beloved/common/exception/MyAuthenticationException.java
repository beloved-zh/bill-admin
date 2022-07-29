package com.beloved.common.exception;

import com.beloved.common.service.StatusCode;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

/**
 * @Author: Beloved
 * @CreateTime: 2022-07-29 09:48
 * @Description: 自定义认证失败异常
 */
@Getter
public class MyAuthenticationException extends AuthenticationException {

    private Integer code;
    private String message;
    
    public MyAuthenticationException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }
    
}
