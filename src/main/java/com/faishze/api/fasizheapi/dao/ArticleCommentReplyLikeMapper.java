package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ArticleCommentReplyLike;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ArticleCommentReplyLikeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleCommentReplyLike record);

    ArticleCommentReplyLike selectByPrimaryKey(Long id);

    List<ArticleCommentReplyLike> selectAll();

    int updateByPrimaryKey(ArticleCommentReplyLike record);
}