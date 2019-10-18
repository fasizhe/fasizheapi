package com.faishze.api.fasizheapi.pojo.do0;

import java.util.Date;

public class ArticleCollection {
    private Long id;

    private Integer userId;

    private Integer articleId;

    private Date createTime;

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

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ArticleCollection{" +
                "id=" + id +
                ", userId=" + userId +
                ", articleId=" + articleId +
                ", createTime=" + createTime +
                '}';
    }
}