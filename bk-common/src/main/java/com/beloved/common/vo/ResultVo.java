package com.beloved.common.vo;

import com.beloved.common.enums.ResultCode;

import java.io.Serializable;

/**
 * 统一返回视图
 *
 * @author beloved
 */
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public ResultVo(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public ResultVo(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultVo<T> success(){
        return new ResultVo(ResultCode.SUCCESS, null);
    }

    public static <T> ResultVo<T> success(T data){
        return new ResultVo(ResultCode.SUCCESS, data);
    }

    public static <T> ResultVo<T> success(String message){
        return new ResultVo(ResultCode.SUCCESS.getCode(), message, null);
    }

    public static <T> ResultVo<T> success(String message, T data){
        return new ResultVo(ResultCode.SUCCESS.getCode(), message, data);
    }

    public static <T> ResultVo<T> error(){
        return new ResultVo(ResultCode.ERROR, null);
    }

    public static <T> ResultVo<T> error(T data){
        return new ResultVo(ResultCode.ERROR, data);
    }

    public static <T> ResultVo<T> error(String message){
        return new ResultVo(ResultCode.ERROR.getCode(), message, null);
    }

    public static <T> ResultVo<T> error(String message, T data){
        return new ResultVo(ResultCode.ERROR.getCode(), message, data);
    }

    public static <T> ResultVo<T> msg(ResultCode resultCode){
        return new ResultVo(resultCode, null);
    }

    public static <T> ResultVo<T> msg(ResultCode resultCode, T data){
        return new ResultVo(resultCode, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
