package com.faishze.api.fasizheapi.exception;

/**
 * 自定义异常，用于验证用户是否已经进行
 * 授权，如果未授权，抛出一个异常
 * @author masonluo
 * @date 2019/10/23 11:20 PM
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg){
        super(msg);
    }

    public UnauthorizedException(){

    }
}
