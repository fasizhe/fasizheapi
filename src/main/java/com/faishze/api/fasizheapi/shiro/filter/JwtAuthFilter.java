package com.faishze.api.fasizheapi.shiro.filter;

import com.faishze.api.fasizheapi.constant.HTTP;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.ErrorResponse;
import com.faishze.api.fasizheapi.shiro.token.JwtAuthenticationToken;
import com.faishze.api.fasizheapi.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author masonluo
 * @date 2019/11/7 4:23 AM
 */
public class JwtAuthFilter extends AuthenticatingFilter {

    private final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    private static final int tokenRefreshInterval = 300;

    public JwtAuthFilter() {
        this.setLoginUrl("/token");
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        String newToken = null;
        if (token instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) token;
            String username = (String) subject.getPrincipal();
            boolean shouldRefresh = shouldTokenRefresh(JwtUtil.getIssuedAt(jwtToken.getToken()));
            if (shouldRefresh) {
                newToken = JwtUtil.JwtBuilder.getBuilder().username(username)
                        .create().getToken();
            }
        }
        if(!StringUtils.isEmpty(newToken)){
            httpServletResponse.setHeader(HTTP.AUTHORIZING_HEADER, newToken);
        }
        return true;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        log.error("login fail");
        return false;
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) //对于OPTION请求做拦截，不做token校验
            return false;

        return super.onPreHandle(request, response,mappedValue);
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        String jwtToken = getAuthToken(request);
        if (!StringUtils.isEmpty(jwtToken) && !JwtUtil.isTokenExpired(jwtToken)) {
            return new JwtAuthenticationToken(jwtToken);
        }
        return null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        fillCorsHeader(WebUtils.toHttp(request), httpServletResponse);
        PrintWriter writer;
        writer = httpServletResponse.getWriter();
        writer.write(getUnAuthorizedJsonBody());
        writer.flush();
        return false;
    }


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 如果是登录请求，则直接放行
        if (isLoginRequest(request, response)) {
            return true;
        }
        boolean allow = false;
        try {
            /**
             * executeLogin会调用createToken方法，然后
             * 调用subject.login进行登录
             */
            allow = executeLogin(request, response);
        } catch (Exception e) {
            log.error("Error occurs when login", e);
            e.printStackTrace();
        }
        // 如果进行宽容处理，则放行
        return allow || isPermissive(mappedValue);
    }

    /**
     * 获取token
     */
    private String getAuthToken(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = ((HttpServletRequest) request).getHeader(HTTP.AUTHORIZING_HEADER);
        return token == null ? token : token.trim();
    }

    private String getUnAuthorizedJsonBody() throws JsonProcessingException {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.UNAUTHORIZED.getError(), ErrorCode.UNAUTHORIZED.getMessage());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(errorResponse);
    }


    /**
     * 判断在固定秒数之后是否需要进行刷新
     * @param issueAt
     * @return
     */
    protected boolean shouldTokenRefresh(Date issueAt){
        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(tokenRefreshInterval).isAfter(issueTime);
    }

    protected void fillCorsHeader(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,HEAD");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
    }
}
