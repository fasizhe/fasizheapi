package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.TArticleCommentReply;
import java.util.List;

public interface TArticleCommentReplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TArticleCommentReply record);

    TArticleCommentReply selectByPrimaryKey(Long id);

    List<TArticleCommentReply> selectAll();

    int updateByPrimaryKey(TArticleCommentReply record);
}