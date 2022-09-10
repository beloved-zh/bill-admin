package com.beloved.common.model.vo;

import com.beloved.common.enums.ResultCode;
import com.beloved.common.service.StatusCode;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回视图
 *
 * @author beloved
 */
@Data
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;
    
    private String message;
    
    private T data;

    // 手动设置返回vo
    public ResultVo(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public ResultVo(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    // 返回指定状态码，数据对象
    public ResultVo(StatusCode statusCode, T data) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.data = data;
    }

    // 只返回状态码
    public ResultVo(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.data = null;
    }

    public ResultVo(T data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getMessage();
        this.data = data;
    }
}
