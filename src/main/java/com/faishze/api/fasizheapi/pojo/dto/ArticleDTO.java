package com.faishze.api.fasizheapi.pojo.dto;

import com.faishze.api.fasizheapi.constant.ArticleType;

import java.util.Date;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/10/27
 */
public class ArticleDTO {
    private Integer id;

    private Integer userId;

    private String userNickName;

    private String title;

    private ArticleType type;

    private Integer likeNum;

    private Integer collectionNum;

    private Long viewNum;

    private Integer commentNum;

    private Boolean available;

    private Date createTime;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
        this.type = type;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }

    public Long getViewNum() {
        return viewNum;
    }

    public void setViewNum(Long viewNum) {
        this.viewNum = viewNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", userNickName='" + userNickName + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", likeNum=" + likeNum +
                ", collectionNum=" + collectionNum +
                ", viewNum=" + viewNum +
                ", commentNum=" + commentNum +
                ", available=" + available +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                '}';
    }
}
