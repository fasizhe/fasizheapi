package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ArticleCommentReply;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCommentReplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleCommentReply record);

    ArticleCommentReply selectByPrimaryKey(Long id);

    List<ArticleCommentReply> selectAll();

    int updateByPrimaryKey(ArticleCommentReply record);
}