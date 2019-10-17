package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.TArticleCommentLike;
import java.util.List;

public interface TArticleCommentLikeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TArticleCommentLike record);

    TArticleCommentLike selectByPrimaryKey(Long id);

    List<TArticleCommentLike> selectAll();

    int updateByPrimaryKey(TArticleCommentLike record);
}