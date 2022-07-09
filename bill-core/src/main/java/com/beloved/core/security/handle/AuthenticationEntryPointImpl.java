package com.beloved.core.security.handle;

import com.alibaba.fastjson2.JSON;
import com.beloved.common.enums.ErrorCode;
import com.beloved.common.model.ResultVo;
import com.beloved.common.utils.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 未认证及 Token 过期处理
 *
 * @author beloved
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ServletUtils.renderString(response, JSON.toJSONString(new ResultVo<>(ErrorCode.UNAUTHORIZED)));
    }
}
