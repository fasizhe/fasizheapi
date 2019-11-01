package com.faishze.api.fasizheapi.pojo.vo;

import java.util.Date;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/10/28
 */
public class ArticleCommentVO {

    private Long id;

    private Integer userId;

    private String userNickName;

    private Integer articleId;

    private String content;

    private Integer replyNum;

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

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
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
        return "ArticleCommentVO{" +
                "id=" + id +
                ", userId=" + userId +
                ", userNickName='" + userNickName + '\'' +
                ", articleId=" + articleId +
                ", content='" + content + '\'' +
                ", replyNum=" + replyNum +
                ", likeNum=" + likeNum +
                ", createTime=" + createTime +
                '}';
    }
}
