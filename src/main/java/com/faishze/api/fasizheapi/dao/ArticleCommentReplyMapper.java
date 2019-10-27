package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.dao.query.ArticleCommentReplyQuery;
import com.faishze.api.fasizheapi.pojo.do0.ArticleCommentReply;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleCommentReplyMapper {
    int deleteArticleCommentReply(Long id);

    int saveArticleCommentReply(ArticleCommentReply record);

    ArticleCommentReply getArticleCommentReply(Long id);

    Page<ArticleCommentReply> listArticleCommentReplys();

    int updateArticleCommentReply(ArticleCommentReply record);

    Page<ArticleCommentReply> listArticleCommentReplysByQuery(ArticleCommentReplyQuery articleCommentReplyQuery);
}