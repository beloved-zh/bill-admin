package com.beloved.core.security.service;

import com.beloved.common.utils.JwtUtil;
import com.beloved.common.utils.StringUtils;
import com.beloved.core.security.bo.LoginUser;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author beloved
 */
@Slf4j
@Component
public class TokenService {

    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // token 前缀
    @Value("${token.token_prefix}")
    private String tokenPrefix;

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${token.expire_time}")
    private int expireTime;

    public String createToken(LoginUser user) {
        // TODO 用户信息缓存 Redis
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        return JwtUtil.create(claims, null, secret);
    }


    public String getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            String username = (String) JwtUtil.getClaimsExcludeExpired(token, secret).get("username");

            // TODO 从 redis 获取用户对象

            return username;
        }
        return null;
    }


    /**
     * 获取请求token
     *
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && StringUtils.startsWith(token, tokenPrefix)) {
            token = StringUtils.replace(token, tokenPrefix, "");
        }
        return token;
    }

}
