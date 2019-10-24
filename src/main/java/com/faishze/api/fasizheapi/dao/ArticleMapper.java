package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.dao.query.ArticleQuery;
import com.faishze.api.fasizheapi.pojo.do0.Article;
import com.github.pagehelper.Page;

public interface ArticleMapper {
    int deleteArticle(Integer id);

    int saveArticle(Article record);

    Article getArticle(Integer id);

    Page<Article> listArticles();

    int updateArticle(Article record);

    //按规则查询所有文章
    Page<Article> listArticlesByQuery(ArticleQuery articleQuery);

}