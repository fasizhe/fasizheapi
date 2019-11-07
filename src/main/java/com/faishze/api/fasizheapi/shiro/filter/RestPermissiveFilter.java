package com.faishze.api.fasizheapi.shiro.filter;

import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一个rest风格的拦截器
 *
 * 拦截规则定义如下:
 *
 * /somePath, restPermissive[somePermission, permissive:somePermission:get|add|update|delete]
 *
 * 进入拦截器后， somePermission会根据HTTP Method自动生成诸如以下形式的权限:
 * GET: somePermission:get
 * POST:somePermission:add
 * PUT: somePermission:update
 * PATCH: somePermission:update
 * DELETE: somePermission:delete
 *
 * 要求用户需要拥有如下对应的权限，才允许对资源进行操作，否则返回401
 *
 * permissive:somePermission:get, permission:somePermission:update
 * 说明对GET和PUT、PATCH进行宽容处理，就算没有这些权限，也允许通行
 * @author masonluo
 * @date 2019/11/5 3:28 AM
 */
public class RestPermissiveFilter extends PermissionsAuthorizationFilter {

    private static final Logger log = LoggerFactory.getLogger(RestPermissiveFilter.class);

    private final Map<String, String> httpMethodActions = new HashMap<String, String>();

    private static final String PERMISSIVE = "permissive";

    private static final String SPLIT = "&";

    /**
     * 将参数解析成诸如user:get, user:create等类型
     */
    private static final String CREATE_ACTION = "add";
    private static final String READ_ACTION = "get";
    private static final String UPDATE_ACTION = "update";
    private static final String DELETE_ACTION = "delete";

    private static enum HttpMethodAction {

        DELETE(DELETE_ACTION),
        GET(READ_ACTION),
        HEAD(READ_ACTION),
        MKCOL(CREATE_ACTION), //webdav, but useful here
        OPTIONS(READ_ACTION),
        POST(CREATE_ACTION),
        PATCH(UPDATE_ACTION),
        PUT(UPDATE_ACTION),
        TRACE(READ_ACTION);

        private final String action;

        private HttpMethodAction(String action) {
            this.action = action;
        }

        public String getAction() {
            return this.action;
        }
    }

    public RestPermissiveFilter(){
        for(HttpMethodAction methodAction : HttpMethodAction.values()){
            httpMethodActions.put(methodAction.name().toLowerCase(), methodAction.getAction());
        }
    }

    protected Map<String, String> getHttpMethodActions() {
        return httpMethodActions;
    }

    protected String getHttpMethodAction(ServletRequest request){
        String method = ((HttpServletRequest) request).getMethod();
        return getHttpMethodAction(method);
    }

    protected String getHttpMethodAction(String method){
        String lc = method.toLowerCase();
        String resolved = getHttpMethodActions().get(lc);
        return resolved != null ? resolved : method;
    }

    protected String[] buildPermissions(HttpServletRequest request, String[] configuredPerms, String action) {
        return buildPermissions(configuredPerms, action);
    }

    private String[] buildPermissions(String[] configuredPerms, String action) {
        if(configuredPerms == null || configuredPerms.length <= 0 || !StringUtils.hasText(action)){
            return configuredPerms;
        }
        List<String> permList = new ArrayList<>();
        List<String> permissiveList = new ArrayList<>();
        for(String perms : configuredPerms){
            if(perms.contains(PERMISSIVE)){
                permissiveList.add(perms.substring(perms.indexOf(":") + 1));
            }else{
                permList.add(perms + ":" + action);
            }
        }
        permList.removeIf(permissiveList::contains);
        String[] mappedParms = new String[permList.size()];
        permList.toArray(mappedParms);
        return mappedParms;
    }

    private void doResolverPermissive(List<String> permissiveActions, String perms) {
        String actions = perms.substring(perms.indexOf("[") + 1, perms.lastIndexOf("]"));
        String[] actionArray = actions.split(SPLIT);
        doResolverPermissive(permissiveActions, actionArray);
    }

    private void doResolverPermissive(List<String> permissiveActions, String[] actions){
        for(String action : actions){
            String lc = action.toLowerCase();
            if(httpMethodActions.containsKey(lc)){
                permissiveActions.add(lc);
            }
        }
    }
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        String[] perms = (String[]) mappedValue;
        // append the http action to the end of the permissions and then back to super
        String action = getHttpMethodAction(request);
        String[] resolvedPerms = buildPermissions(perms, action);
        return super.isAccessAllowed(request, response, resolvedPerms);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        String responseBody = getUnauthorizedResponseBody();
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(responseBody);
        writer.close();
        return false;
    }

    private String getUnauthorizedResponseBody() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.UNAUTHORIZED.getError(), ErrorCode.UNAUTHORIZED.getMessage());
        return mapper.writeValueAsString(errorResponse);
    }
}
