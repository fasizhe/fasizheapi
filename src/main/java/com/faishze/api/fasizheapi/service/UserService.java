package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.pojo.ao.RegisterAO;
import com.faishze.api.fasizheapi.pojo.ao.UserAO;
import com.faishze.api.fasizheapi.pojo.do0.User;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.pojo.dto.Jwt;

import java.util.List;

/**
 * @author masonluo
 * @date 2019/10/23 11:00 PM
 */
public interface UserService {
    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 查找到的用户
     */
    User getByUsername(String username);

    /**
     * 根据user类所拥有的字段去查找所有用户
     * @param user 用户类
     * @return 匹配的用户
     */
    List<User> get(User user);

    Result<Jwt> login(UserAO user);

    User getByUserId(Integer userId);

    String geUsernameByUserId(Integer userId);

    Result register(RegisterAO registerAO);
}