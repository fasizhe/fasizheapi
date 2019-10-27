package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.dao.query.ArticleCommentQuery;
import com.faishze.api.fasizheapi.pojo.do0.ArticleComment;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleCommentMapper {
    int deleteArticleComment(Long id);

    int saveArticleComment(ArticleComment record);

    ArticleComment getArticleComment(Long id);

    Page<ArticleComment> listArticleComments();

    int updateArticleComment(ArticleComment record);

    Page<ArticleComment> listArticleCommentsByQuery(ArticleCommentQuery articleCommentQuery);
}