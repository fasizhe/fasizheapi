package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.TArticleLike;
import java.util.List;

public interface TArticleLikeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TArticleLike record);

    TArticleLike selectByPrimaryKey(Long id);

    List<TArticleLike> selectAll();

    int updateByPrimaryKey(TArticleLike record);
}