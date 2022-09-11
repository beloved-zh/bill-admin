package com.beloved.core.handler.web;

import com.beloved.common.enums.ErrorCode;
import com.beloved.common.exception.MyAuthenticationException;
import com.beloved.common.exception.ServiceException;
import com.beloved.common.model.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 *
 * @author beloved
 */
@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {
    
    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultVo handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                          HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return new ResultVo(ErrorCode.METHOD_NOT_ALLOWED);
    }

    /**
     * 自定义认证异常
     */
    @ExceptionHandler(MyAuthenticationException.class)
    public ResultVo serviceExceptionHandler(MyAuthenticationException e){
        log.error("认证异常-->[{}] {}", e.getCode(), e.getMessage(), e);
        return new ResultVo(e.getCode(), e.getMessage());
    }
    
    /**
     * 自定义异常
     */
    @ExceptionHandler(ServiceException.class)
    public ResultVo serviceExceptionHandler(ServiceException e){
        log.error("业务异常-->[{}] {}", e.getCode(), e.getMessage(), e);
        return new ResultVo(e.getCode(), e.getMessage());
    }
    
    /**
     * 未知异常
     */
    @ExceptionHandler(Exception.class)
    public ResultVo exceptionHandler(Exception e){
        log.error("系统异常：{}", e.getMessage(), e);
        return new ResultVo<>(ErrorCode.DEFAULT);
    }

}
