package com.faishze.api.fasizheapi.controller;

import com.faishze.api.fasizheapi.result.ErrorResponse;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author masonluo
 * @date 2019/10/23 11:35 PM
 */
@RestControllerAdvice
public class ExceptionController {
    // 捕捉shiro异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ErrorResponse handleShiroError(ShiroException e){
        return null;
    }
}
