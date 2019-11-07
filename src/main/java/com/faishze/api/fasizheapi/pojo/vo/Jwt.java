package com.faishze.api.fasizheapi.pojo.vo;

/**
 * @author masonluo
 * @date 2019/11/3 1:22 PM
 */
public class Jwt {
    private String token;

    private Long expiredTime;

    private String username;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
