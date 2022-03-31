package com.beloved.core.security.handle;

import com.alibaba.fastjson.JSONObject;
import com.beloved.common.enums.ResultCode;
import com.beloved.common.utils.ServletUtils;
import com.beloved.common.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证成功处理器
 *
 * @author beloved
 */
@Slf4j
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ServletUtils.renderString(response, JSONObject.toJSONString(ResultVo.success(authentication.getPrincipal())));
    }

}
