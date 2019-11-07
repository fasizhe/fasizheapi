package com.faishze.api.fasizheapi.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author masonluo
 * @date 2019/11/5 4:43 PM
 */
public class PasswordUtil {

    private PasswordUtil(){

    }

    public static final int HASH_ITERATIONS = 2;

    public static final String ALGORITHM_NAME = "SHA-256";

    /**
     * 加密一个密码
     * @param password 密码
     * @param salt 盐值
     * @return 加密后的密码
     */
    public static String encryptPassword(String password, String salt){
        SimpleHash hash = new SimpleHash(ALGORITHM_NAME, password, salt, HASH_ITERATIONS);
        return hash.toHex();
    }

    public static void main(String[] args) {
        System.out.println(encryptPassword("123456","123456"));
    }
}
