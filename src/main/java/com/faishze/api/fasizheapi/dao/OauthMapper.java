package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.enums.OauthType;
import com.faishze.api.fasizheapi.pojo.do0.Oauth;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OauthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Oauth record);

    Oauth selectByPrimaryKey(Integer id);

    List<Oauth> selectAll();

    int updateByPrimaryKey(Oauth record);

    Oauth selectByOauthIdAndOauthType(@Param("openId") String openId, @Param("openType") OauthType oauthType);
}