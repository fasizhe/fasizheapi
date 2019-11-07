package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.pojo.do0.Permission;

import java.util.List;

/**
 * @author masonluo
 * @date 2019/10/23 11:07 PM
 */
public interface PermissionService {
    /**
     * 根据用户名获取所有权限
     * @param username 用户名
     * @return 用户拥有的权限
     */
    List<Permission> listByUsername(String username);

    /**
     * 根据角色名获取所有权限
     * @param roleName 角色名
     * @return 该角色拥有的角色
     */
    List<Permission> listByRoleName(String roleName);

    /**
     * 根据用户名找所有权限名
     * @param username 用户名
     * @return 权限名列表
     */
    List<String> listPermissionNameByUsername(String username);

    /**
     * 根据角色名找所有权限名
     * @param RoleName 角色名
     * @return 权限名列表
     */
    List<String> listPermissionNameByRoleName(String RoleName);

    // TODO
    List<String> getPermissionByRoleName(String roleString);
}
