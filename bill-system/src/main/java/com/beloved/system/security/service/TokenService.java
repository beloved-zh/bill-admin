package com.beloved.system.security.service;

import com.beloved.common.constant.RedisConstants;
import com.beloved.common.constant.TimeConstants;
import com.beloved.common.utils.*;
import com.beloved.common.model.dto.security.LoginUser;
import com.beloved.common.model.dto.security.TokenConfig;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author beloved
 */
@Slf4j
@Component
public class TokenService {

    @Autowired
    private TokenConfig tonkeConfig;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisUtils redisUtils;
    
    public void clearLoginUser(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            return;
        }
        String key = getRedisKey(uuid);
        redisUtils.del(key);
    }
    
    /**
     * 创建 Token 并进行缓存
     * @param user 用户信息
     * @return 令牌
     */
    public String createToken(LoginUser user) {
        String loginId = UUIDUtils.getUUID();
        user.setLonginId(loginId);

        refreshToken(user);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put(RedisConstants.LOGIN_USER_KEY, loginId);

        return jwtUtils.create(claims, null, tonkeConfig.getSecret());
    }


    /**
     * 解析 Token 获取用户信息
     * @param request
     * @return
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        try {
            Claims claims = jwtUtils.getClaims(token, tonkeConfig.getSecret());
            String loginId = (String) claims.get(RedisConstants.LOGIN_USER_KEY);

            String redisKey = getRedisKey(loginId);

            LoginUser user = (LoginUser) redisUtils.get(redisKey);

            return user;
        }catch (Exception e) {
            log.error("Token解析异常：{}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param user 用户信息
     * @return 令牌
     */
    public void verifyToken(LoginUser user) {
        long expireTime = user.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= TimeConstants.MILLIS_MINUTE_TEN) {
            refreshToken(user);
        }
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        String token = ServletUtils.getHeader(request, tonkeConfig.getHeader(), StandardCharsets.UTF_8);
        if (StringUtils.isNotEmpty(token) && StringUtils.startsWith(token, tonkeConfig.getTokenPrefix())) {
            token = StringUtils.replace(token, tonkeConfig.getTokenPrefix(), "");
        }
        return token;
    }

    /**
     * 刷新令牌有效期
     *
     * @param user 用户信息
     */
    public void refreshToken(LoginUser user) {
        user.setLoginTime(System.currentTimeMillis());
        user.setExpireTime(user.getLoginTime() + tonkeConfig.getExpireTime() * TimeConstants.MILLIS_MINUTE);

        String redisKey = getRedisKey(user.getLonginId());
        redisUtils.set(redisKey, user, tonkeConfig.getExpireTime(), TimeUnit.MINUTES);
    }

    /**
     * 获取到期时间
     *
     * @return  到期时间
     */
    private Long getExpiration() {
        return System.currentTimeMillis() + tonkeConfig.getExpireTime() * TimeConstants.MILLIS_MINUTE;
    }

    /**
     * 获取缓存 Redis 的键值
     * @param loginId 登录唯一标识
     * @return
     */
    private String getRedisKey(String loginId) {
        return RedisConstants.LOGIN_USER_KEY + loginId;
    }

}
