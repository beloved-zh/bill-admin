package com.beloved.core.security.service;

import com.beloved.common.constant.RedisConstants;
import com.beloved.common.utils.JwtUtils;
import com.beloved.common.utils.RedisUtils;
import com.beloved.common.utils.StringUtils;
import com.beloved.core.security.bo.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisUtils redisUtils;

    // 毫秒
    protected static final long MILLIS_SECOND = 1000;

    // 分钟
    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    // 默认刷新间隔时间
    protected static final long MILLIS_MINUTE_TEN = 30 * MILLIS_MINUTE;

    /**
     * 创建 Token 并进行缓存
     * @param user 用户信息
     * @return 令牌
     */
    public String createToken(LoginUser user) {
        refreshToken(user);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        return jwtUtils.create(claims, null, secret);
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
        String username = (String) jwtUtils.getClaims(token, secret).get("username");
        LoginUser user = (LoginUser) redisUtils.get(getRedisKey(username));
        return user;
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
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
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
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && StringUtils.startsWith(token, tokenPrefix)) {
            token = StringUtils.replace(token, tokenPrefix, "");
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
        user.setExpireTime(user.getLoginTime() + expireTime * MILLIS_MINUTE);
        redisUtils.set(getRedisKey(user.getUsername()), user, expireTime, TimeUnit.MINUTES);
    }

    /**
     * 获取到期时间
     *
     * @return  到期时间
     */
    private Long getExpiration() {
        return System.currentTimeMillis() + expireTime * MILLIS_MINUTE;
    }

    /**
     * 获取缓存 Redis 的键值
     * @param username
     * @return
     */
    private String getRedisKey(String username) {
        return RedisConstants.LOGIN_USER_KEY + username;
    }

}
