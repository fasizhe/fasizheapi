package com.faishze.api.fasizheapi.manager.impl;

import com.faishze.api.fasizheapi.enums.OauthType;
import com.faishze.api.fasizheapi.global.Redis;
import com.faishze.api.fasizheapi.manager.WeChatManager;
import com.faishze.api.fasizheapi.pojo.do0.Oauth;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.OauthService;
import com.faishze.api.fasizheapi.service.UserService;
import com.faishze.api.fasizheapi.shiro.utils.JwtUtils;
import com.faishze.api.fasizheapi.util.EncryptUtils;
import com.faishze.api.fasizheapi.util.ftp.FTPClientTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
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
    private FTPClientTemplate ftpClientTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private OauthService oauthService;

    @Autowired
    private UserService userService;

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

    /**
     * 微信用户登录，返回结果有三种情况：
     * 1、用户首次登录，需要绑定
     * 2、用户首次登录，但是因某些原因新增失败
     * 3、用户非首次登录，但暂未绑定系统用户
     * 4、用户登录成功
     * @param code
     * @return
     */
    @Override
    public Result login(String code) {
        Code2SessionResponse response = code2Session(code);
        if(!response.getErrcode().equals(0)){
            return Result.fail(ErrorCode.UNAUTHORIZED, response.getErrmsg());
        }
        Oauth wxUser = oauthService.getByOauthIDAndOauthType(response.getOpenid(), OauthType.WECHAT);
        if(wxUser == null){
            Oauth oauth = new Oauth();
            oauth.setOauthId(response.getOpenid());
            oauth.setOauthType(OauthType.WECHAT);
            // 判断是否插入成功
            if(oauthService.add(oauth).getUserId() != null){
                return Result.needBind(generateNeedBindingData(response.getOpenid(), OauthType.WECHAT));
            }else{
                return Result.internalError("服务器错误");
            }
        }
        // 如果未进行绑定，则让其跳转到用户绑定页面
        if(wxUser.getUserId() == null){
            return Result.needBind(response.getOpenid());
        }
        // 绑定了用户，则进行签证
        // TODO 通用mapper，进行根据关键的字段查询
        String username = userService.geUsernameByUserID(wxUser.getUserId());
        Map<String, String> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("open_id", wxUser.getOauthId());
        claims.put("open_type", wxUser.getOauthType().getOauthType());
        return Result.success(JwtUtils.sign(claims));
    }

    /**
     * 进行微信小程序code2Session接口的调用
     * @param code 前端传来的用户临时凭证
     * @return 封装好的返回实体类
     */
    public Code2SessionResponse code2Session(String code){
        Map<String, String> params = new HashMap<>();
        params.put("appid", appID);
        params.put("secret", appSecret);
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        return restTemplate.getForObject(userInfoApi, Code2SessionResponse.class, params);
    }

    /**
     * 需要绑定时，返回给前端的数据
     * @param openID
     * @return
     */
    private Map<String, String> generateNeedBindingData(String openID, OauthType oauthType){
        String code = generateEncryptCode(openID);
        code = EncryptUtils.encrypt(code);
        // 存进redis，以便查验
        // 使用Hash结构, 存放open_id和open_type
        redisTemplate.opsForHash().put(Redis.OAUTH_USER_BINDING_CODE_PREFIX + code, "open_id", openID);
        redisTemplate.opsForHash().put(Redis.OAUTH_USER_BINDING_CODE_PREFIX + code, "open_type_id", oauthType.getOauthId());
        Map<String, String> map = new HashMap<>();
        map.put("target_url", "/oauth/{oauth_id}/user/{username}");
        map.put("code", code);
        map.put("oauth_type", oauthType.getOauthId().toString());
        return map;
    }
    /**
     * 加密openID,获取绑定时需要的凭证
     * @param openID 用户openID
     * @return code
     */
    private String generateEncryptCode(String openID){
        String code = openID + System.currentTimeMillis();
        return EncryptUtils.encrypt(code);
    }

    /**
     * 微信返回实体类
     */
    public static class Code2SessionResponse{
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
