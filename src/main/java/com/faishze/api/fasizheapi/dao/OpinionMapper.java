package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.Opinion;
import java.util.List;

public interface OpinionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Opinion record);

    Opinion selectByPrimaryKey(Long id);

    List<Opinion> selectAll();

    int updateByPrimaryKey(Opinion record);
}