package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.TArticleComment;
import java.util.List;

public interface TArticleCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TArticleComment record);

    TArticleComment selectByPrimaryKey(Long id);

    List<TArticleComment> selectAll();

    int updateByPrimaryKey(TArticleComment record);
}