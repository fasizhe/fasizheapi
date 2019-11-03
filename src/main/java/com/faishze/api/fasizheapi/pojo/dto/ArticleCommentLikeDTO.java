package com.faishze.api.fasizheapi.pojo.dto;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/11/3
 */
public class ArticleCommentLikeDTO {

    private Long id;

    private Long commentId;

    private Integer userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
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
        return "ArticleCommentLikeDTO{" +
                "id=" + id +
                ", commentId=" + commentId +
                ", userId=" + userId +
                '}';
    }
}
