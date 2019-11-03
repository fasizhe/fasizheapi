package com.faishze.api.fasizheapi.pojo.ao;

import javax.validation.constraints.NotNull;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/11/3
 */
public class ArticleLikeAO {

    @NotNull(message = "文章Id不能为空")
    private Integer articleId;

    @NotNull(message = "用户Id不能为空")
    private Integer userId;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ArticleLikeAO{" +
                "articleId=" + articleId +
                ", userId=" + userId +
                '}';
    }
}
