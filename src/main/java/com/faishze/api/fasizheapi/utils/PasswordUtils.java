package com.faishze.api.fasizheapi.utils;

import org.springframework.util.DigestUtils;

/**
 * @author masonluo
 * @date 2019/10/24 10:57 PM
 */
public class PasswordUtils {

    private static final String SALT = "salt";

    public static String encryptPassword(String password){
        String temp = DigestUtils.md5DigestAsHex(password.getBytes());
        return DigestUtils.md5DigestAsHex((temp + SALT).getBytes());
    }
}
