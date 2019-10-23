package com.faishze.api.fasizheapi.shiro.filter;

import com.faishze.api.fasizheapi.global.Global;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.shiro.token.JwtToken;
import com.google.gson.Gson;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author masonluo
 * @date 2019/10/24 12:08 AM
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 默认进行全部拦截
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginAttempt(request, response)){
            try{
                executeLogin(request, response);
                return true;
            }catch (Exception e){
                log.info("login in error:", e.getMessage());
                doUnauthorized(request, response);
                return false;
            }
        }
        return false;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(Global.AUTHORIZATION_HEADER);
        return token == null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader(Global.AUTHORIZATION_HEADER);
        JwtToken token = new JwtToken(authorization);
        getSubject(request, response).login(token);
        return true;
    }

    private void doUnauthorized(ServletRequest request, ServletResponse response){
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            String result = gson.toJson(new Result(false, ErrorCode.UNAUTHORIZED););
            out.write(result);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
