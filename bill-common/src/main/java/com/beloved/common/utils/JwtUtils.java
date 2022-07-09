package com.beloved.common.utils;

import com.beloved.common.exception.ServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 *
 * @author beloved
 */
@Slf4j
@Component
public class JwtUtils {

    // 加密算法
    private final static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    // 最小密钥长度
    private final static Integer MIN_KEY_LENGTH = 4;

    /**
     * 创建令牌
     *
     * @param claims 自定义属性
     * @param expiration 过期时间
     * @param secret 密钥 注意：密钥最短4个字符 负责会抛出 IllegalArgumentException 异常
     * @return JWT令牌
     */
    public String create(Map<String, Object> claims, Date expiration, String secret) {
        if (secret.length() < MIN_KEY_LENGTH) {
            throw new ServiceException("JWT 密钥长度过短");
        }
        claims.put(Claims.ISSUED_AT, new Date());
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiration)
                .signWith(SIGNATURE_ALGORITHM, secret).compact();
        return token;
    }

    /**
     * 获取数据数据声名
     *
     * @param token JWT令牌
     * @param secret 密钥
     * @return 声名数据
     */
    public Claims getClaims(String token, String secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 判断是否过期
     * @param token JWT令牌
     * @param secret 密钥
     * @return 是否过期
     */
    public Boolean isExpiration(String token, String secret) {
        try {
            Claims claims = getClaims(token, secret);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 更新 令牌
     * @param token 旧令牌
     * @param secret 密钥
     * @param expiration 过期时间
     * @return
     */
    public String refresh(String token, String secret, Date expiration) {
        // 获得 JWT 的 Claims
        Claims claims = getClaims(token, secret);
        return create(claims, expiration, secret);
    }

}
