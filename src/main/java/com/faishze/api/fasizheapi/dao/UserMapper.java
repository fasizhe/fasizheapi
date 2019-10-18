package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}