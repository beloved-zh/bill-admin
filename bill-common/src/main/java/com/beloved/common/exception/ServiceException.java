package com.beloved.common.exception;

import com.beloved.common.enums.ErrorCode;
import com.beloved.common.service.StatusCode;
import lombok.Getter;

/**
 * 自定义异常
 *      
 *      code 代表 ErrorCode 的异常状态码
 *      msg  代表业务异常，这只是一个大类，一般前端会放到弹窗title上；
 *      
 *      super(message) 这才是抛出的详细信息
 * @author beloved
 */
@Getter
public class ServiceException extends RuntimeException {

    private Integer code;
    private String message;

    // 手动设置异常
    public ServiceException(StatusCode statusCode) {
        // message用于用户设置抛出错误详情，例如：当前价格-5，小于0
        super(statusCode.getMessage());
        // 状态码
        this.code = statusCode.getCode();
        // 状态码配套的msg
        this.message = statusCode.getMessage();
    }

    // 默认异常使用 APP_ERROR 状态码
    public ServiceException(String message) {
        super(message);
        this.code = ErrorCode.DEFAULT.getCode();
        this.message = message;
    }
}
