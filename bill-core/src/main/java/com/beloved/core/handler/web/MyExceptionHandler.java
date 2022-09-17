package com.beloved.core.handler.web;

import com.beloved.common.enums.ErrorCode;
import com.beloved.common.exception.MyAuthenticationException;
import com.beloved.common.exception.ServiceException;
import com.beloved.common.model.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;

/**
 * 统一异常处理
 *
 * @author beloved
 */
@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {
    
    /**
     * 缺少参数异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultVo handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("缺少参数：{}", e.getParameterName(), e);
        return new ResultVo(ErrorCode.VALIDATE_FAILED.getCode(), MessageFormat.format("缺少参数：{0}", e.getParameterName()));
    }

    /**
     * 单参数校验异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVo handleConstraintViolationException(ConstraintViolationException e) {
        String msg = this.getValidExceptionMsg(e);
        log.error("参数校验异常：{}", e.getMessage(), e);
        return new ResultVo(ErrorCode.VALIDATE_FAILED.getCode(), msg);
    }

    /**
     * get请求的对象参数校验失败后抛出的异常是BindException
     */
    @ExceptionHandler(BindException.class)
    public ResultVo handleBindException(BindException e) {
        String msg = this.getValidExceptionMsg(e);
        log.error("GET请求对象参数校验失败：{}", msg, e);
        return new ResultVo(ErrorCode.VALIDATE_FAILED.getCode(), msg);
    }

    /**
     * post请求的对象参数校验失败后抛出的异常是 MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVo handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = this.getValidExceptionMsg(e);
        log.error("POST请求对象参数校验失败：{}", msg, e);
        return new ResultVo(ErrorCode.VALIDATE_FAILED.getCode(), msg);
    }
    
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

    private String getValidExceptionMsg(ConstraintViolationException e) {
        StringBuilder msg = new StringBuilder("参数校验失败：");
        e.getConstraintViolations().forEach(item -> msg.append("\n").append(item.getMessage()));
        return msg.toString();
    }
    
    private String getValidExceptionMsg(BindException e) {
        StringBuilder msg = new StringBuilder("参数校验失败：");
        e.getBindingResult().getAllErrors().forEach(item -> msg.append("\n").append(item.getDefaultMessage()));
        return msg.toString();
    }
}
