package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.TProfile;
import java.util.List;

public interface TProfileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProfile record);

    TProfile selectByPrimaryKey(Integer id);

    List<TProfile> selectAll();

    int updateByPrimaryKey(TProfile record);
}