package com.faishze.api.fasizheapi.manager.impl;

import com.faishze.api.fasizheapi.manager.WeChatManager;
import com.faishze.api.fasizheapi.result.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-09
 */
@Component("weCharManager")
public class WeChatManagerImpl implements WeChatManager {

    @Value("${mini.program.appID}")
    private String appID;

    @Value("${mini.program.appSecret}")
    private String appSecret;

    @Override
    public Result login(String code) {
        return null;
    }

    public InputStream getAvatar(String accessToken){
        return null;
    }

}
