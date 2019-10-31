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

    /**
     * 根据用户名和密码查询用户
     * @param username 用户名
     * @param password 密码
     */
    User findByUsernameAndPassword(String username, String password);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     */
    User findByUsername(String username);

    /**
     * 根据user里面的字段查询符合条件的用户
     */
    List<User> select(User user);

    /**
     * 根据userID查找用户
     */
    String findUsernameByUserId(Integer userId);
}