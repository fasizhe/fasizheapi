package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.Opinion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Opinion record);

    Opinion selectByPrimaryKey(Long id);

    List<Opinion> selectAll();

    int updateByPrimaryKey(Opinion record);
}