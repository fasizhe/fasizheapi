package com.faishze.api.fasizheapi.util;

import org.springframework.util.DigestUtils;

/**
 * @author masonluo
 * @date 2019/10/24 10:57 PM
 */
public class EncryptUtils {

    // TODO 更改盐值
    private static final String SALT = "fasizhe-salt";

    public static String encrypt(String string){
        String temp = DigestUtils.md5DigestAsHex(string.getBytes());
        return DigestUtils.md5DigestAsHex((temp + SALT).getBytes());
    }
}
