package com.faishze.api.fasizheapi.query;

/**
 * @author 杜科
 * @description 文章评论查询条件
 * @contact 15521177704
 * @since 2019/10/26
 */
public class ArticleCommentQuery {

    //条件
    private Integer articleId;
    private Integer userId;
    private String userNickName;

    private String orderField;
    private String orderType="ASC";

    public ArticleCommentQuery(){}

    public ArticleCommentQuery(Integer articleId, Integer userId, String userNickName, String orderField,
                               String orderType) {
        this.articleId = articleId;
        this.userId = userId;
        this.userNickName = userNickName;
        this.orderField = orderField;
        this.orderType = orderType;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
