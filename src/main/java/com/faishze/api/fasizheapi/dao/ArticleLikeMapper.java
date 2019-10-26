package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ArticleLike;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleLikeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleLike record);

    ArticleLike selectByPrimaryKey(Long id);

    List<ArticleLike> selectAll();

    int updateByPrimaryKey(ArticleLike record);
}