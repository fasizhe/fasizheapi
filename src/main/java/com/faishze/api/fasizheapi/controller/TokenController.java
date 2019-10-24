package com.faishze.api.fasizheapi.controller;

import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.UserService;
import com.faishze.api.fasizheapi.shiro.token.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author masonluo
 * @date 2019/10/23 11:23 PM
 */
@RestController
public class LoginController {
    @Autowired
    public UserService userService;

    @GetMapping("/token")
    public Result<Jwt> login()
}
