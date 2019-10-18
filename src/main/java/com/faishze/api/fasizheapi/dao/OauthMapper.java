package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.Oauth;
import java.util.List;

public interface OauthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Oauth record);

    Oauth selectByPrimaryKey(Integer id);

    List<Oauth> selectAll();

    int updateByPrimaryKey(Oauth record);
}