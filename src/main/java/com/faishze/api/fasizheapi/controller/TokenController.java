package com.faishze.api.fasizheapi.controller;

import com.faishze.api.fasizheapi.pojo.ao.UserAO;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.UserService;
import com.faishze.api.fasizheapi.shiro.token.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author masonluo
 * @date 2019/10/23 11:23 PM
 */
@RestController
public class TokenController {

    @Autowired
    public UserService userService;

    @GetMapping("/token")
    public Result<Jwt> login(@RequestParam String username,
                             @RequestParam String password){
        // 进行初步的处理, 去除空格
        username = StringUtils.trimWhitespace(username);
        password = StringUtils.trimWhitespace(password);
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_IS_BLANK);
        }
        UserAO user = new UserAO(username, password);
        return userService.login(user);
    }


}
