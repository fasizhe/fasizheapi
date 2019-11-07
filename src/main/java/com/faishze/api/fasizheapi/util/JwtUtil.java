package com.faishze.api.fasizheapi.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.faishze.api.fasizheapi.pojo.vo.Jwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * @author masonluo
 * @date 2019/11/3 1:22 PM
 */
public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    private static final String SECRET;

    static {
        SECRET = "SECRET";
    }

    private static final Algorithm DEFAULT_ALGORITHM;

    static {
        DEFAULT_ALGORITHM = Algorithm.HMAC256(SECRET);
    }

    /**
     * 对token进行一个验证
     *
     * @param token  jwt
     * @param leeWay 可接受的过期时间
     */
    public static boolean verify(String token, Long leeWay) {
        JWTVerifier verifier = JWT.require(DEFAULT_ALGORITHM)
                .acceptLeeway(leeWay)
                .build();
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            log.warn("token {} verify error.", token);
            return false;
        }
    }

    public static Date getIssuedAt (String token){
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getIssuedAt();
        }catch (JWTDecodeException e){
            return null;
        }
    }
    public static boolean isTokenExpired(String token){
        Date now = Calendar.getInstance().getTime();
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().before(now);
    }

    /**
     * 验证jwt 是否有效
     *
     * @param token jwt
     */
    public static boolean verify(String token) {
        return verify(token, 0L);
    }

    /**
     * 获得jwt的声明中的username
     * @param token jwt
     */
    public static String getUsername(String token) {
        Claim claim = getClaim(token, "username");
        if (claim != null) {
            return claim.asString();
        }
        return null;
    }

    /**
     * 获取jwt声明中的openid（如果存在）
     * @param token jwt
     */
    public static String getOpenId(String token) {
        Claim claim = getClaim(token, "openId");
        if (claim != null) {
            return claim.asString();
        }
        return null;
    }

    public static String getOpenType(String token) {
        Claim claim = getClaim(token, "openType");
        if (claim != null) {
            return claim.asString();
        }
        return null;
    }

    public static String[] getRoles(String token) {
        Claim claim = getClaim(token, "roles");
        if (claim != null) {
            return claim.asArray(String.class);
        }
        return null;
    }

    public static String[] getPermissions(String token) {
        Claim claim = getClaim(token, "permissions");
        if (claim != null) {
            return claim.asArray(String.class);
        }
        return null;
    }

    private static Claim getClaim(String token, String key) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key);
        } catch (JWTDecodeException e) {
            log.info("token verify error.");
            return null;
        }
    }

     public static class JwtBuilder {
        private JWTCreator.Builder creator = JWT.create();

        private final Jwt jwt;

        private Long expiredSecond = 7200L;

        public JwtBuilder() {
            // 默认过期时间为7200秒
            jwt = new Jwt();
            expiredSecond = 7200L;
        }

        @Deprecated
        public JwtBuilder setClaim(String key, String value) {
            creator.withClaim(key, value);
            return this;
        }

        public JwtBuilder username(String value) {
            creator.withClaim("username", value);
            jwt.setUsername(value);
            return this;
        }

        public JwtBuilder expiredSecond(Long second) {
            expiredSecond = second;
            return this;
        }

        public JwtBuilder openId(String openId) {
            creator.withClaim("openId", openId);
            return this;
        }

        public JwtBuilder openType(String openType) {
            creator.withClaim("openType", openType);
            return this;
        }

        public JwtBuilder roles(String[] array) {
            creator.withArrayClaim("roles", array);
            return this;
        }

        public JwtBuilder permissions(String[] array) {
            creator.withArrayClaim("permissions", array);
            return this;
        }

        public static JwtBuilder getBuilder() {
            return new JwtBuilder();
        }

        public Jwt create() {
            String token = creator.withExpiresAt(new Date(System.currentTimeMillis() + expiredSecond * 1000))
                    .withIssuedAt(new Date())
                    .sign(DEFAULT_ALGORITHM);
            jwt.setToken(token);
            jwt.setExpiredTime(expiredSecond);
            return jwt;
        }
    }

    public static void main(String[] args) {
        JwtBuilder builder = new JwtBuilder();
        Jwt jwt = builder.username("username")
                .expiredSecond(10L)
                .openId("openID")
                .openType("openType")
                .roles(new String[]{"role1", "role2"})
                .permissions(new String[]{"permission1", "permission2"})
                .create();
        System.out.println();
        System.out.println(JwtUtil.getUsername(jwt.getToken()));
        System.out.println(JwtUtil.getOpenId(jwt.getToken()));
        System.out.println(JwtUtil.getOpenType(jwt.getToken()));
        System.out.println(Arrays.toString(JwtUtil.getPermissions(jwt.getToken())));
        System.out.println(Arrays.toString(JwtUtil.getRoles(jwt.getToken())));
        System.out.println(JwtUtil.verify(jwt.getToken()));
        System.out.println(JwtUtil.verify(jwt.getToken(), 10L));
        System.out.println();
    }
}
