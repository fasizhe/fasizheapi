package com.faishze.api.fasizheapi.shiro.realm;

import com.faishze.api.fasizheapi.pojo.do0.User;
import com.faishze.api.fasizheapi.service.UserService;
import com.faishze.api.fasizheapi.shiro.matcher.JwtCredentialsMatcher;
import com.faishze.api.fasizheapi.shiro.token.JwtAuthenticationToken;
import com.faishze.api.fasizheapi.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author masonluo
 * @date 2019/11/7 4:03 AM
 */
public class JwtRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    public JwtRealm(){
        setCredentialsMatcher(new JwtCredentialsMatcher());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtAuthenticationToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authcToken;
        String token = jwtToken.getToken();
        User user = userService.getByUsername(JwtUtil.getUsername(token));
        if(user == null){
            throw new AuthenticationException("token过期，请重新登录");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(JwtUtil.getUsername(token), token, getName());
        return info;
    }

    @Override
    public String getName() {
        return "JwtRealm";
    }
}
