package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.TRole;
import java.util.List;

public interface TRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRole record);

    TRole selectByPrimaryKey(Integer id);

    List<TRole> selectAll();

    int updateByPrimaryKey(TRole record);
}