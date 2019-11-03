package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.pojo.do0.Role;

import java.util.List;

/**
 * @author masonluo
 * @date 2019/10/23 11:06 PM
 */
public interface RoleService {

    /**
     * 根据用户名获取角色列表
     * @param username 用户名
     * @return 用户拥有的角色
     */
    List<Role> listRolesByUsername(String username);

    /**
     * 根据用户名获取角色名
     * @param username
     * @return
     */
    List<String> listRoleNamesByUsername(String username);
}
