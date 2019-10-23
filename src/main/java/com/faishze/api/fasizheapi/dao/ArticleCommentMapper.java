package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ArticleComment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleComment record);

    ArticleComment selectByPrimaryKey(Long id);

    List<ArticleComment> selectAll();

    int updateByPrimaryKey(ArticleComment record);
}