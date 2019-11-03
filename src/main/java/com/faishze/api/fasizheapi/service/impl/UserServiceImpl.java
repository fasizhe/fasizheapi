package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.UserMapper;
import com.faishze.api.fasizheapi.pojo.ao.RegisterAO;
import com.faishze.api.fasizheapi.pojo.ao.UserAO;
import com.faishze.api.fasizheapi.pojo.do0.User;
import com.faishze.api.fasizheapi.pojo.dto.UserDTO;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.UserService;
import com.faishze.api.fasizheapi.pojo.dto.Jwt;
import com.faishze.api.fasizheapi.shiro.utils.JwtUtils;
import com.faishze.api.fasizheapi.util.EncryptUtils;
import com.google.gson.Gson;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
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

    private final Mapper dozerBeanMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, Mapper dozerBeanMapper) {
        this.userMapper = userMapper;
        this.dozerBeanMapper = dozerBeanMapper;
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
    public Result register(RegisterAO registerAO) {
        String phoneNum = registerAO.getPhoneNum();
        User user = userMapper.findByUsername(phoneNum);
        // 检查用户是否已经存在
        if(user != null){
            return Result.fail(ErrorCode.FORBIDDEN, phoneNum + "已经存在");
        }
        user = new User();
        user.setStatus(true);
        user.setUsername(phoneNum);
        user.setPassword(EncryptUtils.encrypt(registerAO.getPassword()));
        int result = userMapper.insert(user);
        if(result == 0){
            return Result.internalError("注册失败");
        }
        UserDTO userDTO = dozerBeanMapper.map(user, UserDTO.class);
        return new Result<>(true, userDTO);
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
