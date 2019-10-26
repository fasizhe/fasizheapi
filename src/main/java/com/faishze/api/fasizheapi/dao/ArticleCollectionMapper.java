package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ArticleCollection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCollectionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleCollection record);

    ArticleCollection selectByPrimaryKey(Long id);

    List<ArticleCollection> selectAll();

    int updateByPrimaryKey(ArticleCollection record);
}