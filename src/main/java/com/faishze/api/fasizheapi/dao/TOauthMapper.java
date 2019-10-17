package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.TOauth;
import java.util.List;

public interface TOauthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TOauth record);

    TOauth selectByPrimaryKey(Integer id);

    List<TOauth> selectAll();

    int updateByPrimaryKey(TOauth record);
}