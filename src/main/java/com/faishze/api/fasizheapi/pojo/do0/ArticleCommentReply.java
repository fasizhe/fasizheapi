package com.faishze.api.fasizheapi.pojo.do0;

import java.util.Date;

public class ArticleCommentReply{
    private Long id;

    private Integer userId;

    private Integer articleId;

    private Long commentId;

    private String replyUserId;

    private String content;

    private Integer likeNum;

    private Date createTime;

    private Date updateTime;

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

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId == null ? null : replyUserId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ArticleCommentReply{" +
                "id=" + id +
                ", userId=" + userId +
                ", articleId=" + articleId +
                ", commentId=" + commentId +
                ", replyUserId='" + replyUserId + '\'' +
                ", content='" + content + '\'' +
                ", likeNum=" + likeNum +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}