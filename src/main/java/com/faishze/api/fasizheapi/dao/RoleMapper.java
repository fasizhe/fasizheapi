package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    /**
     * 获取username的所有角色
     * @param username 用户名
     * @return 角色列表
     */
    List<Role> listRolesByUsername(String username);

    /**
     * 获取username的所有Role的名称
     * @param username 用户名
     * @return 角色名称列表
     */
    List<String> listRoleNamesByUsername(String username);
}