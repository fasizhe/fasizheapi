package com.faishze.api.fasizheapi.shiro.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.faishze.api.fasizheapi.pojo.dto.Jwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author masonluo
 * @date 2019/10/23 1:03 PM
 */
@Component
public class JwtUtils{

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    // 定义过期时间(ms)
    private static final long EXPIRE_TIME = 5 * 60 * 100;
    // 定义失效允许时间(s)
    private static final long LEE_WAY = 2 * 60 * 60;
    // 系统密钥
    @Value("${shiro.private.srcret}")
    private static String secret;

    /**
     * 验证一个jwt是否有效
     * @param token jwt token
     * @param username token的拥有者
     * @return token是否有效
     */
    public static boolean verify(String token, String username) {
        return verify(token, username, 0);
    }

    /**
     * 验证一个jwt是否在可接受失效的时间内有效
     * @param token jwt
     * @param username 用户名
     * @param leeWay 可接受的失效时间
     * @return 是否有效
     */
    private static boolean verify(String token, String username, long leeWay){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .acceptLeeway(leeWay)
                .withClaim("username", username)
                .build();
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return true;
        } catch (JWTVerificationException ex) {
            log.info("token {} vertify error!", token);
            return false;
        }
    }

    /**
     * 获取jwt的用户名
     * @param token jwt
     * @return 用户名
     */
    public static String getUsername(String token){
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        }catch (JWTDecodeException e){
            log.info("token {} vertify error!", token);
            return null;
        }
    }

    /**
     * 进行jwt的签发
     * @param username 用户名
     * @return 返回一个jwt字符串
     */
    public static Jwt sign(String username){
        try{
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            Jwt jwt = new Jwt();
            jwt.setExpireTime(date);
            jwt.setUsername(username);
            jwt.setToken(JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm));
            return jwt;
        }catch (Exception e){
            log.error("sing jwt error", e.getMessage());
            return null;
        }
    }

    /**
     * 进行重新签发，如果jwt过期，
     * 且过期时间小于可接受的过期
     * 时间的话，重新签发
     * @param token jwt
     * @return 签发的jwt
     */
    public static Jwt reSign(String token){
        String username = getUsername(token);
        if(!verify(token, username) && verify(token, username, LEE_WAY)){
            return sign(username);
        }
        return null;
    }
}
