package com.beloved.core.config;

import com.alibaba.fastjson.JSONObject;
import com.beloved.common.enums.ResultCode;
import com.beloved.common.exception.ServiceException;
import com.beloved.common.vo.ResultVo;
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
public class ExceptionHandlerConfig {

    /**
     * 自定义异常
     */
    @ExceptionHandler(ServiceException.class)
    public JSONObject serviceExceptionHandler(ServiceException e){
        log.error("业务异常-->{}", e.getMessage(), e);
        return ResultVo.error(e.getMessage());
    }

    /**
     * Http请求方法不支持异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JSONObject httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e){
        log.error("Http请求方法不支持异常：{}", e.getMessage(), e);
        return ResultVo.error(ResultCode.METHOD_NOT_ALLOWED.getMessage());
    }

    /**
     * 未知异常
     */
    @ExceptionHandler(Exception.class)
    public JSONObject exceptionHandler(HttpServletRequest request, Exception e){
        log.error("系统异常：{}", e.getMessage(), e);
        return ResultVo.error();
    }

}
