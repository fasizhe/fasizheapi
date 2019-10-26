package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ArticleLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleLikeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleLike record);

    ArticleLike selectByPrimaryKey(Long id);

    List<ArticleLike> selectAll();

    int updateByPrimaryKey(ArticleLike record);
}