package com.faishze.api.fasizheapi.pojo.vo;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/11/3
 */
public class ArticleCommentReplyLikeVO {

    private Long id;

    private Long replyId;

    private Integer userId;

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

    @Override
    public String toString() {
        return "ArticleCommentReplyLikeVO{" +
                "id=" + id +
                ", replyId=" + replyId +
                ", userId=" + userId +
                '}';
    }
}
