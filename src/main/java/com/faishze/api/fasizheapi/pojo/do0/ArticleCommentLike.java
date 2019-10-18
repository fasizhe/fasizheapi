package com.faishze.api.fasizheapi.pojo.do0;

import java.util.Date;

public class ArticleCommentLike{
    private Long id;

    private Integer commentId;

    private Integer userId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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
        return "ArticleCommentLike{" +
                "id=" + id +
                ", commentId=" + commentId +
                ", userId=" + userId +
                ", createTime=" + createTime +
                '}';
    }
}