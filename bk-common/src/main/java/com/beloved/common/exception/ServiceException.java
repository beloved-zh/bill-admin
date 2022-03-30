package com.beloved.common.exception;

import com.beloved.common.enums.ResultCode;

/**
 * 自定义异常
 *
 * @author beloved
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ResultCode resultCode;

    private Integer code;

    private String message;

    public ServiceException() {
    }

    public ServiceException(ResultCode resultCode) {
        this.resultCode = resultCode;
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
