package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.enums.OauthType;
import com.faishze.api.fasizheapi.pojo.do0.Oauth;

/**
 * @author masonluo
 * @date 2019/10/27 4:27 PM
 */
public interface OauthService {
    Oauth add(Oauth oauth);

    /**
     * 根据openID和oauth类型找到一个记录
     * @param openId
     * @param oauthType
     */
    Oauth getByOauthIdAndOauthType(String openId, OauthType oauthType);

    /**
     * 绑定用户和第三方记录
     * @param userId
     * @param openId
     * @param oauthType
     * @return
     */
    boolean bind(Integer userId, String openId, OauthType oauthType);
}
