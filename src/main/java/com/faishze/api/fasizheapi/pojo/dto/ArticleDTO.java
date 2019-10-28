package com.faishze.api.fasizheapi.pojo.dto;

import com.faishze.api.fasizheapi.pojo.do0.Article;

/**
 * @author 杜科
 * @description 封装作者名字和文章
 * @contact 15521177704
 * @since 2019/10/27
 */
public class ArticleDTO {

    //作者名字
    private String authorName;

    private Article article;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "authorName='" + authorName + '\'' +
                ", article=" + article +
                '}';
    }
}
