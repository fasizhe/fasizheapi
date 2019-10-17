package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.TPermission;
import java.util.List;

public interface TPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPermission record);

    TPermission selectByPrimaryKey(Integer id);

    List<TPermission> selectAll();

    int updateByPrimaryKey(TPermission record);
}