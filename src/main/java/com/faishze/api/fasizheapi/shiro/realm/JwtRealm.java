package com.faishze.api.fasizheapi.shiro.realm;

import com.faishze.api.fasizheapi.pojo.do0.User;
import com.faishze.api.fasizheapi.service.PermissionService;
import com.faishze.api.fasizheapi.service.RoleService;
import com.faishze.api.fasizheapi.service.UserService;
import com.faishze.api.fasizheapi.shiro.token.JwtToken;
import com.faishze.api.fasizheapi.shiro.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author masonluo
 * @date 2019/10/23 11:43 PM
 */
@Service
public class JwtRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(JwtRealm.class);

    private final UserService userService;

    private final RoleService roleService;

    private final PermissionService permissionService;

    @Autowired
    public JwtRealm(PermissionService permissionService, RoleService roleService, UserService userService) {
        this.permissionService = permissionService;
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "JwtRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = (String) principalCollection.getPrimaryPrincipal();
        String username = JwtUtils.getUsername(token);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> roleNameList = roleService.listRoleNameByUsername(username);
        List<String> permissionNameList = permissionService.listPermissionNameByUsername(username);
        info.addRoles(roleNameList);
        info.addStringPermissions(permissionNameList);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String username = JwtUtils.getUsername(token);
        if(username == null){
            throw new AuthenticationException("token invalid");
        }
        User user = userService.getByUsername(username);
        if(user == null){
            throw new AuthenticationException("User doesn't not existed!");
        }
        if(!JwtUtils.verify(token, username)){
            throw new AuthenticationException("Username or password error!");
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
