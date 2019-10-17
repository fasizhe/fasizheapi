package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.TArticle;
import java.util.List;

public interface TArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TArticle record);

    TArticle selectByPrimaryKey(Integer id);

    List<TArticle> selectAll();

    int updateByPrimaryKey(TArticle record);
}