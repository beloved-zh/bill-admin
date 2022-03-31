package com.beloved.core.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.beloved.common.enums.ResultCode;
import com.beloved.common.exception.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // 判断是否是 post 请求
        if (!request.getMethod().equals("POST")) {
            log.error("登录方式非法请求。请求方式：{}", request.getMethod());
            return null;
        }

        // 判断是否是 json 格式请求参数
        if (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
            try {
                // 获取请求中参数
                JSONObject userinfo = new ObjectMapper().readValue(request.getInputStream(), JSONObject.class);
                // 调用父类中的获取字段定义 可复用 后期可通过注入改变参数名
                String username = userinfo.getString(getUsernameParameter());
                String password = userinfo.getString(getPasswordParameter());

                log.debug("username：{}", username);
                log.debug("password：{}", password);

                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
                setDetails(request, authRequest);

                return this.getAuthenticationManager().authenticate(authRequest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 如果不是 json 调用父类实现通过表单获取
        return super.attemptAuthentication(request, response);
    }

}
