package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.TOpinion;
import java.util.List;

public interface TOpinionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TOpinion record);

    TOpinion selectByPrimaryKey(Long id);

    List<TOpinion> selectAll();

    int updateByPrimaryKey(TOpinion record);
}