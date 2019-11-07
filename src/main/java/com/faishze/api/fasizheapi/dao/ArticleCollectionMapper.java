package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.entity.AritcleCollectionEntity;
import com.faishze.api.fasizheapi.pojo.do0.ArticleCollection;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCollectionMapper {
    int deleteArticleCollection(Long id);

    int saveArticleCollection(ArticleCollection record);

    ArticleCollection getArticleCollection(Long id);

    List<ArticleCollection> listArticleCollections();

    int updatetArticleCollection(ArticleCollection record);

    //返回收藏者收藏的文章，id降序即收藏时间降序，由新到旧
    Page<AritcleCollectionEntity> listArticleCollectionEntitysByCollectorId(Integer userId);
}