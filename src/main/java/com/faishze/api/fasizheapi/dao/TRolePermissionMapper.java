package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.TRolePermission;
import java.util.List;

public interface TRolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRolePermission record);

    TRolePermission selectByPrimaryKey(Integer id);

    List<TRolePermission> selectAll();

    int updateByPrimaryKey(TRolePermission record);
}