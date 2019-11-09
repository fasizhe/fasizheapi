package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ArticleLike;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ArticleLikeMapper {
    int deleteArticleLike(Long id);

    int saveArticleLike(ArticleLike record);

    ArticleLike getArticleLike(Long id);

    Page<ArticleLike> listArticleLikes();

    int updateArticleLike(ArticleLike record);

    //用于判断该用户是否点赞过这篇文章
    ArticleLike getArticleLikeByUserIdAndArticleId(Integer userId,Integer articleId);

    Page<ArticleLike> listArticleLikesByArticleIdAndDeadTime(Integer articleId, Date deadTime);
}