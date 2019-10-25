package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.Oauth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OauthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Oauth record);

    Oauth selectByPrimaryKey(Integer id);

    List<Oauth> selectAll();

    int updateByPrimaryKey(Oauth record);
}