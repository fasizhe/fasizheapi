package com.faishze.api.fasizheapi.controller;

import com.faishze.api.fasizheapi.manager.WeChatManager;
import com.faishze.api.fasizheapi.pojo.ao.UserAO;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.ErrorResponse;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.UserService;
import com.faishze.api.fasizheapi.shiro.token.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final UserService userService;

    private final WeChatManager weChatManager;

    @Autowired
    public TokenController(UserService userService, WeChatManager weChatManager) {
        this.userService = userService;
        this.weChatManager = weChatManager;
    }

    /**
     * 用户登录接口
     * @param username 用户名
     * @param password 密码
     * @return 一个携带jwt的Token或者错误码
     */
    @GetMapping("/token")
    public Object login(@RequestParam("username") String username,
                             @RequestParam("password") String password){
        // 进行初步的处理, 去除空格
        username = StringUtils.trimWhitespace(username);
        password = StringUtils.trimWhitespace(password);
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorCode(ErrorCode.INVALID_PARAMETER_IS_BLANK.getError());
            errorResponse.setMessage("Param invalidate");
            ResponseEntity<ErrorResponse> responseEntity =
                    new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        UserAO user = new UserAO(username, password);
        return userService.login(user);
    }

    public Result<Jwt> weChatLogin(@RequestParam("code") String code){
        if(StringUtils.isEmpty(code)){
            return Result.fail(ErrorCode.INVALID_PARAMETER_IS_BLANK);
        }
        Result result = weChatManager.login(code);
        return null;
    }
}
