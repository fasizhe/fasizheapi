package com.faishze.api.fasizheapi.shiro.config;

import com.faishze.api.fasizheapi.shiro.filter.JwtAuthFilter;
import com.faishze.api.fasizheapi.shiro.filter.RestPermissiveFilter;
import com.faishze.api.fasizheapi.shiro.realm.JwtRealm;
import com.faishze.api.fasizheapi.shiro.realm.UserRealm;
import com.faishze.api.fasizheapi.shiro.resolver.MyRolePermissionResolver;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.mgt.SubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author masonluo
 * @date 2019/11/3 11:55 AM
 */
@Configuration
public class ShiroConfig {

    /**
     * 角色权限处理器
     * 解析出角色的权限
     */
    @Bean("rolePermissionResolver")
    public RolePermissionResolver createRolePermissionResolver() {
        return new MyRolePermissionResolver();
    }

    /**
     * 禁用session，但是还是需要配合noSessionFilter使用
     */
    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator() {
        DefaultWebSessionStorageEvaluator evaluator = new DefaultWebSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);
        return evaluator;
    }

    /**
     * 验证者
     */
    @Bean
    public Authenticator authenticator() {
        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

    /**
     * 权限验证
     *
     * @param rolePermissionResolver 用户权限解析器
     */
    @Bean
    public Authorizer authorizer(RolePermissionResolver rolePermissionResolver) {
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
        authorizer.setRolePermissionResolver(rolePermissionResolver);
        return authorizer;
    }

    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    @Bean
    public JwtRealm jwtRealm(){
        return new JwtRealm();
    }

    @Bean("securityManager")
    public SecurityManager securityManager(SessionStorageEvaluator evaluator,
                                           Authenticator authenticator,
                                           Authorizer authorizer,
                                           UserRealm userRealm,
                                           JwtRealm jwtRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        SubjectDAO subjectDAO = new DefaultSubjectDAO();
        ((DefaultSubjectDAO) subjectDAO).setSessionStorageEvaluator(evaluator);
        securityManager.setAuthenticator(authenticator);
        securityManager.setAuthorizer(authorizer);
        securityManager.setRealms(Arrays.asList(userRealm, jwtRealm));
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("restPermissive", restPermissiveFilter());
        filterMap.put("authcToken", jwtAuthFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());
        return shiroFilterFactoryBean;
    }

    @Bean
    protected ShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/token", "noSessionCreation, anon");
        chainDefinition.addPathDefinition("/article", "noSessionCreation, authcToken[permissive],restPermissive[article, permissive:article:get, permissive:article:add]");
        chainDefinition.addPathDefinition("/user", "noSessionCreation, authcToken[permissive], restPermissive[user, permissive:user:add]");
        chainDefinition.addPathDefinition("/admin", "noSessionCreation, authcToken, restPermissive[admin]");
        return chainDefinition;
    }

    private RestPermissiveFilter restPermissiveFilter(){
        return new RestPermissiveFilter();
    }

    private JwtAuthFilter jwtAuthFilter(){
        return new JwtAuthFilter();
    }
}
