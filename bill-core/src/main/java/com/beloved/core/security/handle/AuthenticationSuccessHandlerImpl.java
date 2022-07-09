package com.beloved.core.security.handle;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.beloved.common.model.ResultVo;
import com.beloved.common.utils.ServletUtils;
import com.beloved.core.security.bo.LoginUser;
import com.beloved.core.security.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // token 前缀
    @Value("${token.token_prefix}")
    private String tokenPrefix;

    @Autowired
    private TokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String token = tokenService.createToken((LoginUser) authentication.getPrincipal());

        JSONObject data = new JSONObject();
        data.put("header", header);
        data.put("tokenPrefix", tokenPrefix);
        data.put("token", token);

        ServletUtils.renderString(response, JSON.toJSONString(new ResultVo<>(data)));
    }

}
