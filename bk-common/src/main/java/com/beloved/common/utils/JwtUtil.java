package com.beloved.common.utils;

import com.beloved.common.exception.ServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 *
 * @author beloved
 */
public class JwtUtil {

    //加密算法
    private final static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private final static Integer MIN_KEY_LENGTH = 4;

    public static void main(String[] args) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("username", "beloved");
        System.out.println(create(claims, new Date(System.currentTimeMillis() + 100000), "abccba"));
        System.out.println(create(claims, new Date(System.currentTimeMillis()), "abac"));

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDg4MjE2MDAsInVzZXJuYW1lIjoiYmVsb3ZlZCJ9.xrw0Mt7TEtfK_Xa0gLByra9ia6FE4ePOVstzt7X7TZ8";
        String secret = "abccba";

//        System.out.println(getClaims(token, secret));
//        System.out.println(getSubject(token, secret));
        System.out.println(getExpiration(token, secret));
        System.out.println(isExpiration(token, secret));
    }

    /**
     * 生成 JWT
     * @param claims 自定义属性
     * @param secret 密钥 注意：密钥最短4个字符 负责会抛出 IllegalArgumentException 异常
     * @return JWT令牌
     */
    public static String create(Map<String, Object> claims, Date expiration, String secret) {
        if (secret.length() < MIN_KEY_LENGTH) {
            throw new ServiceException("JWT 密钥长度过短");
        }
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiration)
                .signWith(SIGNATURE_ALGORITHM, secret).compact();
        return token;
    }

    private static String create(Claims claims, Date expiration, String secret) {
        if (secret.length() < MIN_KEY_LENGTH) {
            throw new ServiceException("JWT 密钥长度过短");
        }
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiration)
                .signWith(SIGNATURE_ALGORITHM, secret).compact();
        return token;
    }

    /**
     * 获取主体信息
     *      注意：过期会抛出异常 ExpiredJwtException
     *           解析失败抛出异常 SignatureException
     * @param token JWT令牌
     * @param secret 密钥
     * @return 主体信息
     */
    public static Claims getClaims(String token, String secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取主体信息
     *      注意：过期 JWT 仍可以获取主体信息
     *           解析失败返回 null
     * @param token JWT令牌
     * @param secret 密钥
     * @return
     */
    public static Claims getClaimsExcludeExpired(String token, String secret) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            // 如果过期，在异常中调用, 返回claims, 否则无法解析过期的token
            claims = e.getClaims();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 获取用户信息
     * @param token JWT令牌
     * @param secret 密钥
     * @return 用户信息
     */
    public static String getSubject(String token, String secret) {
        return getClaims(token, secret).getSubject();
    }

    /**
     * 获取过期时间
     * @param token JWT令牌
     * @param secret 密钥
     * @return 过期时间
     */
    public static Date getExpiration(String token, String secret) {
        return getClaimsExcludeExpired(token, secret).getExpiration();
    }

    /**
     * 判断是否过期
     * @param token JWT令牌
     * @param secret 密钥
     * @return 是否过期
     */
    public static Boolean isExpiration(String token, String secret) {
        return getExpiration(token, secret).before(new Date());
    }


    /**
     * 更新 JWT
     * @param token 旧令牌
     * @param secret 密钥
     * @param expiration 过期时间
     * @return
     */
    public String refresh(String token, String secret, Date expiration) {
        // 获得 JWT 的 Claims
        Claims claims = getClaimsExcludeExpired(token, secret);
        return create(claims, expiration, secret);
    }

}
