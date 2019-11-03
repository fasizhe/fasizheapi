package com.faishze.api.fasizheapi.pojo.vo;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/11/3
 */
public class ArticleCommentLikeVO {

    private Long id;

    private Integer commentId;

    private Integer userId;

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

    @Override
    public String toString() {
        return "ArticleCommentLikeVO{" +
                "id=" + id +
                ", commentId=" + commentId +
                ", userId=" + userId +
                '}';
    }
}
