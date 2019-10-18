package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ArticleCommentReply;
import java.util.List;

public interface ArticleCommentReplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleCommentReply record);

    ArticleCommentReply selectByPrimaryKey(Long id);

    List<ArticleCommentReply> selectAll();

    int updateByPrimaryKey(ArticleCommentReply record);
}