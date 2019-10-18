package com.faishze.api.fasizheapi.pojo.do0;

import java.util.Date;

public class ArticleLike {
    private Long id;

    private Integer articleId;

    private Integer userId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ArticleLike{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", userId=" + userId +
                ", createTime=" + createTime +
                '}';
    }
}