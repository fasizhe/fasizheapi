package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.pojo.do0.Permission;
import com.faishze.api.fasizheapi.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO 完善PermissionServiceImpl
 * @author masonluo
 * @date 2019/10/23 11:48 PM
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Override
    public List<Permission> listByUsername(String username) {
        return null;
    }

    @Override
    public List<Permission> listByRoleName(String roleName) {
        return null;
    }

    @Override
    public List<String> listPermissionNameByUsername(String username) {
        return null;
    }

    @Override
    public List<String> listPermissionNameByRoleName(String RoleName) {
        return null;
    }

    @Override
    public List<String> getPermissionByRoleName(String roleString) {
        return null;
    }
}
