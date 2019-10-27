package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.pojo.do0.Oauth;

/**
 * @author masonluo
 * @date 2019/10/27 4:27 PM
 */
public interface OauthService {
    Oauth add(Oauth oauth);

    Oauth getByOauthID(String openID);
}
