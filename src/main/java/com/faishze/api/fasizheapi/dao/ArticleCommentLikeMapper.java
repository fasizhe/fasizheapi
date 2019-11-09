package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ArticleCommentLike;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface ArticleCommentLikeMapper {
    int deleteArticleCommentLike(Long id);

    int saveArticleCommentLike(ArticleCommentLike record);

    ArticleCommentLike getArticleCommentLike(Long id);

    Page<ArticleCommentLike> listArticleCommentLikes();

    int updateArticleCommentLike(ArticleCommentLike record);

    ArticleCommentLike getArticleCommentLikeByUserIdAndCommentId(Integer userId, Long commmentId);

    Page<ArticleCommentLike> listArticleCommentLikesByCommentIdAndDeadTime(Long commentId, Date deadTime);
}