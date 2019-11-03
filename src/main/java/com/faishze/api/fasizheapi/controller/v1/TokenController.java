package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.manager.WeChatManager;
import com.faishze.api.fasizheapi.pojo.ao.UserAO;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.UserService;
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
    private final UserService userService;

    private final WeChatManager weChatManager;

    @Autowired
    public TokenController(UserService userService, WeChatManager weChatManager) {
        this.userService = userService;
        this.weChatManager = weChatManager;
    }

    /**
     * showdoc
     * 用户登录获取token接口
     *
     * @param username 必选 string 用户名
     * @param password 必选 string 密码
     * @return 一个携带jwt的Token或者错误码
     */
    @GetMapping("/token")
    public Object login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        // 进行初步的处理, 去除空格
        username = StringUtils.trimWhitespace(username);
        password = StringUtils.trimWhitespace(password);
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return Result.invalidParameterIsBlank();
        }
        // 直接返回service层返回的数据
        UserAO user = new UserAO(username, password);
        return userService.login(user);
    }

    /**
     * 微信用户进行登录，获取token
     *
     * @param code 登录凭证，微信端下发的
     */
    @GetMapping("/token/wx")
    public Object weChatLogin(@RequestParam("code") String code) {
        if (StringUtils.isEmpty(code)) {
            return Result.fail(ErrorCode.INVALID_PARAMETER_IS_BLANK);
        }
        Result result = weChatManager.login(code);
        return null;
    }
}
