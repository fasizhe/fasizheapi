package com.faishze.api.fasizheapi.pojo.do0;

import java.io.Serializable;
import java.util.Date;

public class ArticleCommentReplyLike implements Serializable {
    private Long id;

    private Long replyId;

    private Integer userId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
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
        return "ArticleCommentReplyLike{" +
                "id=" + id +
                ", replyId=" + replyId +
                ", userId=" + userId +
                ", createTime=" + createTime +
                '}';
    }
}