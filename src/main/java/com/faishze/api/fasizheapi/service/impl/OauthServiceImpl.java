package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.OauthMapper;
import com.faishze.api.fasizheapi.enums.OauthType;
import com.faishze.api.fasizheapi.pojo.do0.Oauth;
import com.faishze.api.fasizheapi.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author masonluo
 * @date 2019/10/27 11:28 PM
 */
@Service
public class OauthServiceImpl implements OauthService {

    private final OauthMapper oauthMapper;

    @Autowired
    public OauthServiceImpl(OauthMapper oauthMapper) {
        this.oauthMapper = oauthMapper;
    }

    @Override
    public Oauth add(Oauth oauth) {
        int result = oauthMapper.insert(oauth);
        if(result == 0){
            return null;
        }
        return oauth;
    }

    @Override
    public Oauth getByOauthIdAndOauthType(String openID, OauthType oauthType) {
        return oauthMapper.selectByOauthIdAndOauthType(openID, oauthType);
    }

    @Override
    public boolean bind(Integer userId, String openId, OauthType oauthType) {
        Oauth query = new Oauth();
        query.setOauthId(openId);
        query.setOauthType(oauthType);
        // 查找表中是否有这条记录
        Oauth record = oauthMapper.selectByOauthIdAndOauthType(openId, oauthType);
        if(record == null){
            return false;
        }
        query = new Oauth();
        query.setId(record.getId());
        query.setUserId(userId);
        int result = oauthMapper.updateByPrimaryKey(query);
        if(result == 0){
            return false;
        }
        return true;
    }

}
