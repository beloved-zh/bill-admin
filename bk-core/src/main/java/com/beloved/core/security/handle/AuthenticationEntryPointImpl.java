package com.beloved.core.security.handle;

import com.alibaba.fastjson.JSONObject;
import com.beloved.common.enums.ResultCode;
import com.beloved.common.utils.ServletUtils;
import com.beloved.common.vo.ResultVo;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 未认证处理
 *
 * @author beloved
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ServletUtils.renderString(response, JSONObject.toJSONString(ResultVo.error(ResultCode.UNAUTHORIZED, "尚未认证")));
    }
}
