package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-09
 */
@Mapper
public interface UserMapper {
    UserDO getUser(Integer id);

    UserDO getUserByUsername(String username);

    int saveUser(UserDO userDO);

    int delete(Integer id);

    List<UserDO> listUsers(String username);

}
