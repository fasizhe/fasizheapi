package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.UserMapper;
import com.faishze.api.fasizheapi.pojo.ao.UserAO;
import com.faishze.api.fasizheapi.pojo.dto.UserDTO;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 描述:用户服务层
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @update 2019-09-22 11:41
 * @create 2019-09-22 11:41
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    /**
     * UserMapper
     */
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<UserDTO> saveUser(UserAO userAO) {
        return null;
    }

    @Override
    public Result<UserDTO> getUser(Integer id) {
        Result<UserDTO> result = saveUser(null);
        if (!result.isSuccess()) {
            return result;
        }



        return null;
    }

}
