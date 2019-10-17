package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.TArticleCommentReplyLike;
import java.util.List;

public interface TArticleCommentReplyLikeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TArticleCommentReplyLike record);

    TArticleCommentReplyLike selectByPrimaryKey(Long id);

    List<TArticleCommentReplyLike> selectAll();

    int updateByPrimaryKey(TArticleCommentReplyLike record);
}