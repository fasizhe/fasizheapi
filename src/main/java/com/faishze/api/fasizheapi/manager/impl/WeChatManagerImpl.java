package com.faishze.api.fasizheapi.manager.impl;

import com.faishze.api.fasizheapi.manager.WeChatManager;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.util.ftp.FTPClientTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述: 微信业务
 *
 * @author xhsf
 * @author masonluo
 * @email 827032783@qq.com
 * @create 2019-10-09
 */
@Component("weCharManager")
public class WeChatManagerImpl implements WeChatManager {

    private Logger log = LoggerFactory.getLogger(WeChatManagerImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    FTPClientTemplate ftpClientTemplate;

    @Value("${mini.program.appID}")
    private String appID;

    @Value("${mini.program.appSecret}")
    private String appSecret;

    // 微信accessTokenapi接口
    @Value("mini.program.api.codeSessionApi")
    private String code2SessionApi;

    // 微信用户信息api接口
    @Value("mini.program.api.userInfo")
    private String userInfoApi;

    @Override
    public Result login(String code) {
        Code2SessionResult apiResult = code2SessionApi(code);
        if(!apiResult.getErrcode().equals(0)){
            return Result.unAuthorization();
        }
        return null;
    }

    /**
     * 调用code2SessionAPI进行获取access_token等参数
     * @param code 前端传来的code
     * @return 封装好的微信返回值
     */
    @Override
    public Code2SessionResult code2SessionApi(String code) {
        RestTemplate restTemplate = new RestTemplate();
        // 构造参数
        Map<String, String> params = new HashMap<>();
        params.put("appid", appID);
        params.put("secret", appSecret);
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        Code2SessionResult result = restTemplate.getForObject(code2SessionApi, Code2SessionResult.class, params);
        return result;
    }

    @Override
    public String getAvatar(String accessToken, String openID) {
        return null;
    }

    /**
     * 微信返回实体类
     */
    public static class Code2SessionResult{
        private String openid;

        private String session_key;

        private String unionid;

        private Integer errcode = 0;

        private String errmsg;

        private int expires_in;

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getSession_key() {
            return session_key;
        }

        public void setSession_key(String session_key) {
            this.session_key = session_key;
        }

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
        }

        public Integer getErrcode() {
            return errcode;
        }

        public void setErrcode(Integer errcode) {
            this.errcode = errcode;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }
    }
}
