package com.faishze.api.fasizheapi.pojo.do0.entity;

import com.faishze.api.fasizheapi.pojo.do0.Article;

import java.util.Date;

/**
 * @author 杜科
 * @description 文章收藏的实体类，封装文章和被收藏的时间
 * @contact 15521177704
 * @since 2019/10/26
 */
public class AritcleCollectionEntity {

    private Long id;

    private Integer userId;

    private Article article;

    private Date collectedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Date getCollectedTime() {
        return collectedTime;
    }

    public void setCollectedTime(Date collectedTime) {
        this.collectedTime = collectedTime;
    }

    @Override
    public String toString() {
        return "AritcleCollectionEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", article=" + article +
                ", collectedTime=" + collectedTime +
                '}';
    }
}
