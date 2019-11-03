package com.faishze.api.fasizheapi.pojo.ao;

import javax.validation.constraints.NotNull;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/11/3
 */
public class ArticleCommentReplyLikeAO {

    @NotNull(message = "回复Id不能为空")
    private Long replyId;

    @NotNull(message = "用户Id不能为空")
    private Integer userId;

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
        return "ArticleCommentReplyLikeAO{" +
                "replyId=" + replyId +
                ", userId=" + userId +
                '}';
    }
}
