package com.faishze.api.fasizheapi.shiro.realm;

import com.faishze.api.fasizheapi.pojo.do0.User;
import com.faishze.api.fasizheapi.service.RoleService;
import com.faishze.api.fasizheapi.service.UserService;
import com.faishze.api.fasizheapi.util.PasswordUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author masonluo
 * @date 2019/11/5 4:04 PM
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 默认构造方法，设置一个matcher
     */
    public UserRealm(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(PasswordUtil.ALGORITHM_NAME);
        matcher.setHashIterations(PasswordUtil.HASH_ITERATIONS);
        matcher.setStoredCredentialsHexEncoded(true);
        setCredentialsMatcher(matcher);
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        Set<String> roleNameList = roleService.listRoleNamesByUsername(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleNameList);
        return info;
    }

    @Override
    public String getName() {
        return "UserRealm";
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        User user = userService.getByUsername(username);
        if (user == null) {
            throw new AuthenticationException("用户验证失败");
        }
        return new SimpleAuthenticationInfo(username, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
    }
}
