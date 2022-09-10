package com.beloved.system.security.handle;

import com.alibaba.fastjson2.JSON;
import com.beloved.common.enums.ErrorCode;
import com.beloved.common.exception.MyAuthenticationException;
import com.beloved.common.model.vo.ResultVo;
import com.beloved.common.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证失败处理器
 *      AuthenticationException 异常处理
 * @author beloved
 */
@Slf4j
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.error("认证失败：{}", e.getMessage(), e);
        if (e instanceof MyAuthenticationException) {
            MyAuthenticationException err = (MyAuthenticationException) e;
            ServletUtils.writeJson(response, JSON.toJSONString(new ResultVo<>(err.getCode(), e.getMessage())));
        } else {
            ServletUtils.writeJson(response, JSON.toJSONString(new ResultVo<>(ErrorCode.AUTH_FAIL)));
        }
    }

}
