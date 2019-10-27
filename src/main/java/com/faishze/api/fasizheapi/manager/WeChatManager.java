package com.faishze.api.fasizheapi.manager;

import com.faishze.api.fasizheapi.result.Result;

import java.util.Map;

/**
 * 描述:
 *
 * @author xhsf
 * @email 827032783@qq.com
 * @create 2019-10-09 15:07
 */
public interface WeChatManager {
    Result login(String code);

    Map<String, Object> getAccessToken(String code);

    String getAvatar(String accessToken, String openID);
}
