package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.pojo.ao.UserAO;
import com.faishze.api.fasizheapi.pojo.dto.UserDTO;
import com.faishze.api.fasizheapi.result.Result;

/**
 * 描述: 用户Service
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-9
 */
public interface UserService {

    Result<UserDTO> saveUser(UserAO userAO);

    Result<UserDTO> getUser(Integer id);

}
