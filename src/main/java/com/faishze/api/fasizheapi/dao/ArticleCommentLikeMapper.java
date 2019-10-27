package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ArticleCommentLike;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleCommentLikeMapper {
    int deleteArticleComment(Long id);

    int saveArticleComment(ArticleCommentLike record);

    ArticleCommentLike getArticleComment(Long id);

    Page<ArticleCommentLike> listArticleComments();

    int updateArticleComment(ArticleCommentLike record);

 
}