package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ArticleCommentReplyLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleCommentReplyLikeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleCommentReplyLike record);

    ArticleCommentReplyLike selectByPrimaryKey(Long id);

    List<ArticleCommentReplyLike> selectAll();

    int updateByPrimaryKey(ArticleCommentReplyLike record);
}