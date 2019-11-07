package com.faishze.api.fasizheapi.shiro.resolver;

import com.faishze.api.fasizheapi.service.PermissionService;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author masonluo
 * @date 2019/11/5 5:10 PM
 */
public class MyRolePermissionResolver implements RolePermissionResolver {

    @Autowired
    private PermissionService permissionService;

    @Override
    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        List<String> permissionList = permissionService.getPermissionByRoleName(roleString);
        List<Permission> permissions = new ArrayList<>();
        for(String perm : permissionList){
            permissions.add(new WildcardPermission(perm));
        }
        return permissions;
    }
}
