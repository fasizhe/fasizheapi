package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.TUserRole;
import java.util.List;

public interface TUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserRole record);

    TUserRole selectByPrimaryKey(Integer id);

    List<TUserRole> selectAll();

    int updateByPrimaryKey(TUserRole record);
}