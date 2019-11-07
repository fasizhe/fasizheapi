package com.faishze.api.fasizheapi.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author masonluo
 * @date 2019/11/3 11:15 PM
 */
public class JwtAuthenticationToken implements AuthenticationToken {

    private String token;

    public JwtAuthenticationToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public String getToken() {
        return token;
    }
}
