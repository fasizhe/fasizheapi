package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.UserMapper;
import com.faishze.api.fasizheapi.pojo.ao.UserAO;
import com.faishze.api.fasizheapi.pojo.do0.User;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.UserService;
import com.faishze.api.fasizheapi.pojo.dto.Jwt;
import com.faishze.api.fasizheapi.shiro.utils.JwtUtils;
import com.faishze.api.fasizheapi.util.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author masonluo
 * @date 2019/10/23 11:48 PM
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public List<User> get(User user) {
        return userMapper.select(user);
    }

    @Override
    public User getByUserId(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public String geUsernameByUserId(Integer userId) {
        return userMapper.findUsernameByUserId(userId);
    }

    @Override
    public Result<Jwt> login(UserAO user) {
        user.setPassword(EncryptUtils.encrypt(user.getPassword()));
        User userDo = userMapper.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(userDo != null){
            // 判断用户是否被锁定
            if(!userDo.getStatus()){
                return Result.fail(ErrorCode.FORBIDDEN, "用户已被锁定");
            }
            // 正常，签发jwt
            Jwt jwt = JwtUtils.sign(user.getUsername());
            return Result.success(jwt);
        }
        // 用户名密码不匹配
        return Result.fail(ErrorCode.UNAUTHORIZED, " 用户名或者密码不正确");
    }
}
