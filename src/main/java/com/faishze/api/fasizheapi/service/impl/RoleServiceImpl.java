package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.pojo.do0.Role;
import com.faishze.api.fasizheapi.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author masonluo
 * @date 2019/10/23 11:48 PM
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> listByUsername(String username) {
        return null;
    }

    @Override
    public List<String> listRoleNameByUsername(String username) {
        return null;
    }
}
