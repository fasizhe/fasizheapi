package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ArticleCollection;
import java.util.List;

public interface ArticleCollectionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleCollection record);

    ArticleCollection selectByPrimaryKey(Long id);

    List<ArticleCollection> selectAll();

    int updateByPrimaryKey(ArticleCollection record);
}