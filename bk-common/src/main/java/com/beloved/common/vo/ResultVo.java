package com.beloved.common.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beloved.common.enums.ResultCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 统一返回视图
 *
 * @author beloved
 */
public class ResultVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String CODE = "code";
    private static final String MESSAGE = "message";
    private static final String DATA = "data";

    public static JSONObject result(ResultCode code){
        JSONObject result = new JSONObject();
        result.put(CODE, code.getCode());
        result.put(MESSAGE, code.getMessage());
        return result;
    }

    public static JSONObject result(ResultCode code, Object data){
        JSONObject result = new JSONObject();
        result.put(CODE, code.getCode());
        result.put(MESSAGE, code.getMessage());
        result.put(DATA, data);
        return result;
    }


    public static JSONObject success(){
        return result(ResultCode.SUCCESS);
    }

    public static JSONObject success(Object data){
        return result(ResultCode.SUCCESS, data);
    }

    public static JSONObject success(String message){
        JSONObject result = new JSONObject();
        result.put(CODE, ResultCode.SUCCESS.getCode());
        result.put(MESSAGE, message);
        return result;
    }

    public static JSONObject success(String message, Object data){
        JSONObject result = new JSONObject();
        result.put(CODE, ResultCode.SUCCESS.getCode());
        result.put(MESSAGE, message);
        result.put(DATA, data);
        return result;
    }

    public static JSONObject error(){
        return result(ResultCode.ERROR);
    }

    public static JSONObject error(Object data){
        return result(ResultCode.ERROR, data);
    }

    public static JSONObject error(String message){
        JSONObject result = new JSONObject();
        result.put(CODE, ResultCode.ERROR.getCode());
        result.put(MESSAGE, message);
        return result;
    }

    public static JSONObject error(String message, Object data){
        JSONObject result = new JSONObject();
        result.put(CODE, ResultCode.ERROR.getCode());
        result.put(MESSAGE, message);
        result.put(DATA, data);
        return result;
    }

}
