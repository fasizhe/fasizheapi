package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author masonluo
 * @date 2019/10/23 11:22 PM
 */
@RestController
public class UserController {

    /**
     * TODO 用户注册逻辑
     */
    @PostMapping("/user")
    public Result register(@RequestParam("phoneNum") String phoneNum,
                           @RequestParam("validateCode") String validateCode){
        return null;
    }
}
