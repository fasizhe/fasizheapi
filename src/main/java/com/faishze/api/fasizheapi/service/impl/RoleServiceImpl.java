package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.RoleMapper;
import com.faishze.api.fasizheapi.pojo.do0.Role;
import com.faishze.api.fasizheapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author masonluo
 * @date 2019/10/23 11:48 PM
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<Role> listRolesByUsername(String username) {
        return roleMapper.listRolesByUsername(username);
    }

    @Override
    public Set<String> listRoleNamesByUsername(String username) {
        return roleMapper.listRoleNamesByUsername(username);
    }
}
