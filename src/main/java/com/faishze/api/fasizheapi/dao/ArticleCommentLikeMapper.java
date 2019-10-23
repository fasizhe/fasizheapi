package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ArticleCommentLike;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCommentLikeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleCommentLike record);

    ArticleCommentLike selectByPrimaryKey(Long id);

    List<ArticleCommentLike> selectAll();

    int updateByPrimaryKey(ArticleCommentLike record);
}