package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ArticleLike;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleLikeMapper {
    int deleteArticleLike(Long id);

    int saveArticleLike(ArticleLike record);

    ArticleLike getArticleLike(Long id);

    Page<ArticleLike> listArticleLikes();

    int updateArticleLike(ArticleLike record);

    ArticleLike getArticleLikeByUserIdAndArticleId(Integer userId,Integer articleId);
}