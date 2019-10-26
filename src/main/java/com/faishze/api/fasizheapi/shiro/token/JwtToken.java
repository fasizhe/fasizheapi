package com.faishze.api.fasizheapi.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 *@author masonluo
 *@date 2019/10/23 11:28 PM
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token){
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
}
