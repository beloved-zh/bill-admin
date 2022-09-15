package com.beloved.core.handler.web;

import com.beloved.common.annotation.RawData;
import com.beloved.common.enums.ErrorCode;
import com.beloved.common.exception.ServiceException;
import com.beloved.common.model.vo.ResultVo;
import com.beloved.common.utils.JsonUtils;
import com.beloved.common.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.LinkedHashMap;

/**
 * @Author: Beloved
 * @CreateTime: 2022-07-09 17:58
 * @Description: 统一响应体
 */
@Slf4j
@RestControllerAdvice
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {

    @Autowired
    private JsonUtils jsonUtils;
    
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // response是ResultVo类型，或者注释了@RawData都不进行包装
        return !(returnType.getParameterType().isAssignableFrom(ResultVo.class) || returnType.hasMethodAnnotation(RawData.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // String类型不能直接包装
        if (returnType.getGenericParameterType().equals(String.class)) {
            return jsonUtils.toJSONString(new ResultVo<>(body));
        }
        // 解决404、500等 spring 没有捕获的异常问题
        if (body instanceof LinkedHashMap) {
            LinkedHashMap map = (LinkedHashMap)body;
            // 防止返回本身数据是 LinkedHashMap 根据 spring 异常格式判断
            if (
                    ObjectUtils.isNotEmpty(map.get("status")) &&
                    ObjectUtils.isNotEmpty(map.get("status")) &&
                    ObjectUtils.isNotEmpty(map.get("message")) &&
                    ObjectUtils.isNotEmpty(map.get("path"))
            ) {
                throw new ServiceException(ErrorCode.DEFAULT);
            }
        }
        // 否则直接包装成ResultVo返回
        return new ResultVo<>(body);
    }
}
