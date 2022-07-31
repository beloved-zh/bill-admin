package com.beloved.system.security.handle;

import com.alibaba.fastjson2.JSON;
import com.beloved.common.enums.ErrorCode;
import com.beloved.common.model.ResultVo;
import com.beloved.common.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 匿名用户访问无权限资源自定义异常处理器
 * @author beloved
 */
@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.error(e.getMessage());
        ServletUtils.writeJson(response, JSON.toJSONString(new ResultVo<>(ErrorCode.UNAUTHORIZED)));
    }
}
