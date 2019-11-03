package com.faishze.api.fasizheapi.pojo.ao;

import javax.validation.constraints.NotNull;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/11/2
 */
public class ArticleCollectionAO {

    @NotNull(message = "用户Id不能为空")
    private Integer userId;

    @NotNull(message = "文章Id不能为空")
    private Integer articleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "ArticleCollectionAO{" +
                "userId=" + userId +
                ", articleId=" + articleId +
                '}';
    }
}
