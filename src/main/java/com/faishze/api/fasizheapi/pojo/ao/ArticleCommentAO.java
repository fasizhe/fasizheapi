package com.faishze.api.fasizheapi.pojo.ao;

import javax.validation.constraints.NotNull;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/11/1
 */
public class ArticleCommentAO {

    @NotNull(message = "用户Id不能为空")
    private Integer userId;

    @NotNull(message = "用户昵称不能为空")
    private String userNickName;

    @NotNull(message = "文章Id不能为空")
    private Integer articleId;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleCommentAO{" +
                "userId=" + userId +
                ", userNickName='" + userNickName + '\'' +
                ", articleId=" + articleId +
                ", content='" + content + '\'' +
                '}';
    }
}
