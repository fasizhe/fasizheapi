package com.faishze.api.fasizheapi.pojo.dto;

import java.util.Date;

/**
 * @author 杜科
 * @description 封装回复发起者名字，接受者名字
 * @contact 15521177704
 * @since 2019/10/28
 */
public class ArticleCommentReplyDTO {

    private Long id;

    private Integer userId;

    private String userNickName;

    private Integer articleId;

    private Long commentId;

    private Integer replyUserId;

    private String replyUserNickName;

    private String content;

    private Integer likeNum;

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

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
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

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyUserNickName() {
        return replyUserNickName;
    }

    public void setReplyUserNickName(String replyUserNickName) {
        this.replyUserNickName = replyUserNickName;
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

    @Override
    public String toString() {
        return "ArticleCommentReplyDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", userNickName='" + userNickName + '\'' +
                ", articleId=" + articleId +
                ", commentId=" + commentId +
                ", replyUserId=" + replyUserId +
                ", replyUserNickName='" + replyUserNickName + '\'' +
                ", content='" + content + '\'' +
                ", likeNum=" + likeNum +
                ", createTime=" + createTime +
                '}';
    }
}
