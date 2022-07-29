package com.beloved.system.security.filter;

import com.alibaba.fastjson2.JSONObject;
import com.beloved.common.enums.ErrorCode;
import com.beloved.common.exception.MyAuthenticationException;
import com.beloved.core.config.BillConfig;
import com.beloved.system.service.CaptchaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义 UsernamePasswordAuthenticationFilter
 *
 * @author beloved
 */
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private CaptchaService captchaService;
    
    @Autowired
    private BillConfig billConfig;
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // 判断是否是 post 请求
        if (!request.getMethod().equalsIgnoreCase("POST")) {
            throw new MyAuthenticationException(ErrorCode.METHOD_NOT_ALLOWED);
        }

        // 判断是否是 json 格式请求参数
        if (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
            // 获取请求中参数
            JSONObject userinfo = new JSONObject();
            
            try {
                userinfo = new ObjectMapper().readValue(request.getInputStream(), JSONObject.class);
                log.debug("userinfo：{}", userinfo);
            } catch (IOException e){
                log.error(e.getMessage(), e);
            }

            if (billConfig.getCaptchaOnOff()) {
                String uuid = userinfo.getString("uuid");
                String code = userinfo.getString("code");
                captchaService.verify(uuid, code);
            }
            
            // 调用父类中的获取字段定义 可复用 后期可通过注入改变参数名
            String username = userinfo.getString(getUsernameParameter());
            String password = userinfo.getString(getPasswordParameter());

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            // 如果不是 json 调用父类实现通过表单获取

            if (billConfig.getCaptchaOnOff()) {
                // 先验证验证码
                String uuid = request.getParameter("uuid");
                String code = request.getParameter("code");
                captchaService.verify(uuid, code);
            }
            
            return super.attemptAuthentication(request, response);
        }
    }

}
