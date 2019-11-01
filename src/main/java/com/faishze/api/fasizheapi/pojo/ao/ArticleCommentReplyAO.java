package com.faishze.api.fasizheapi.pojo.ao;

import javax.validation.constraints.NotNull;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/11/1
 */
public class ArticleCommentReplyAO {

    @NotNull(message = "用户Id不能为空")
    private Integer userId;

    @NotNull(message = "用户昵称不能为空")
    private String userNickName;

    @NotNull(message = "文章Id不能为空")
    private Integer articleId;

    @NotNull(message = "评论Id不能为空")
    private Long commentId;

    @NotNull(message = "被回复用户Id不能为空")
    private Integer replyUserId;

    @NotNull(message = "被回复用户昵称不能为空")
    private String replyUserNickName;

    @NotNull(message = "内容不能为空")
    private String content;

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
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleCommentReplyAO{" +
                "userId=" + userId +
                ", userNickName='" + userNickName + '\'' +
                ", articleId=" + articleId +
                ", commentId=" + commentId +
                ", replyUserId=" + replyUserId +
                ", replyUserNickName='" + replyUserNickName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
