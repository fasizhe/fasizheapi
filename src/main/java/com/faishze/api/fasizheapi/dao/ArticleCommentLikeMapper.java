package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ArticleCommentLike;
import java.util.List;

public interface ArticleCommentLikeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleCommentLike record);

    ArticleCommentLike selectByPrimaryKey(Long id);

    List<ArticleCommentLike> selectAll();

    int updateByPrimaryKey(ArticleCommentLike record);
}