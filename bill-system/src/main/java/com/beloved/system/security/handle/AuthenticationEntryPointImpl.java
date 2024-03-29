package com.beloved.system.security.handle;

import com.beloved.common.enums.ErrorCode;
import com.beloved.common.model.vo.ResultVo;
import com.beloved.common.utils.JsonUtils;
import com.beloved.common.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 匿名用户访问无权限资源自定义异常处理器
 * @author beloved
 */
@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Autowired
    private JsonUtils jsonUtils;
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.error("未授权访问：{}", e.getMessage(), e);
        ServletUtils.writeJson(response, jsonUtils.toJSONString(new ResultVo<>(ErrorCode.UNAUTHORIZED)));
    }
}
