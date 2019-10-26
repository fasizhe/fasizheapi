package com.faishze.api.fasizheapi.manager.impl;

import com.faishze.api.fasizheapi.manager.WeChatManager;
import com.faishze.api.fasizheapi.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-09
 */
@Component("weCharManager")
public class WeChatManagerImpl implements WeChatManager {

    private Logger log = LoggerFactory.getLogger(WeChatManagerImpl.class);

    @Value("${mini.program.appID}")
    private String appID;

    @Value("${mini.program.appSecret}")
    private String appSecret;

    // 微信accessTokenapi接口
    @Value("mini.program.api.accessToken")
    private String accessTokenApi;

    // 微信用户信息api接口
    @Value("mini.program.api.userInfo")
    private String userInfoApi;

    @Override
    public Result login(String code) {
        return null;
    }

    @Override
    public Map getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        // 构造参数
        Map<String, String> params = new HashMap<>();
        params.put("appid", appID);
        params.put("secret", appSecret);
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        Map result = restTemplate.getForObject(accessTokenApi, Map.class, params);
        if(result.get("access_token") == null){
            log.warn("获取accessToken失败 —— error code:{}, error message: {}", result.get("errorcde"), result.get("errormsg"));
            return null;
        }
        return result;
    }

    @Override
    public String getAvatar(String accessToken, String openID) {
        return null;
    }
}
