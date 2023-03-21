package com.dyh.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Date;

public final class JwtUtil {
    /**
     * 这个密钥是防止JWT被篡改的关键，但是长度不能小于4
     */
    private final static String secretKey = "pixel";

    /**
     * 过期时间目前设置为2小时，这个配置随业务需求而定
     */
    private final static Duration expiration = Duration.ofHours(24);

    /**
     * 生成JWT
     * @param userName 用户名
     * @return  JWT
     */
    public static String generate(String userName) {
        // 过期时间
        Date expiryDate = new Date(System.currentTimeMillis()+expiration.toMillis());

        return Jwts.builder()
                .setSubject(userName) // 将userName放进JWT
                .setIssuedAt(new Date()) // 设置JWT签发时间
                .setExpiration(expiryDate) // 设置过期时间
                .signWith(SignatureAlgorithm.HS512,secretKey) // 设置加密算法和密钥
                .compact();
    }

    /**
     * 解析JWT
     * @param token JWT字符串
     * @return  解析成功返回Claims对象，解析失败返回null
     */
    public static Claims parse(String token) {
        // 如果是空字符串直接返回null
        if(StringUtils.isEmpty(token)) {
            return null;
        }

        // 这个Claims对象包含了许多属性，比如签发时间，过期时间以及存放的数据等
        Claims claims = null;
        // 解析失败了会抛出异常，所以我们要捕捉一下。token过期，token非法都会导致解析失败
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey) // 设置密钥
                    .parseClaimsJws(token)
                    .getBody();
        } catch(JwtException e) {
            // 这里应该用日志输出，为了演示方便就直接打印了
            System.out.println("解析失败!");
        }
        return claims;
    }


}
