package com.faishze.api.fasizheapi.pojo.ao;

import javax.validation.constraints.NotNull;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/11/3
 */
public class ArticleCommentLikeAO {

    @NotNull(message = "评论Id不能为空")
    private Long commentId;

    @NotNull(message = "用户Id不能为空")
    private Integer userId;

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
        return "ArticleCommentLikeAO{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                '}';
    }
}
