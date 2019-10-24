package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User getByUsernameAndPassword(String username, String password);
}