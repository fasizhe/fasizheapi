package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.TArticleCollection;
import java.util.List;

public interface TArticleCollectionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TArticleCollection record);

    TArticleCollection selectByPrimaryKey(Long id);

    List<TArticleCollection> selectAll();

    int updateByPrimaryKey(TArticleCollection record);
}