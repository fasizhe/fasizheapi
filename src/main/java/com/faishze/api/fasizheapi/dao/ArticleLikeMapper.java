package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ArticleLike;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleLikeMapper {
    int deleteArticleLike(Long id);

    int saveArticleLike(ArticleLike record);

    ArticleLike getArticleLike(Long id);

    List<ArticleLike> listArticleLikes();

    int updateArticleLike(ArticleLike record);

}