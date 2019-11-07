package com.faishze.api.fasizheapi.shiro.matcher;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.faishze.api.fasizheapi.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author masonluo
 * @date 2019/11/7 4:53 AM
 */
public class JwtCredentialsMatcher implements CredentialsMatcher {

    private final Logger log = LoggerFactory.getLogger(JwtCredentialsMatcher.class);

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo info) {
        String token = (String) authenticationToken.getPrincipal();
        Object stored = info.getPrincipals();
        try{
            return JwtUtil.verify(token);
        }catch (JWTVerificationException e){
            log.error("Token Error {}", token);
        }
        return false;
    }
}
