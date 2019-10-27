package com.faishze.api.fasizheapi.dao.query;

/**
 * @author 杜科
 * @description 文章评论查询条件
 * @contact 15521177704
 * @since 2019/10/26
 */
public class ArticleCommentQuery {
    //要排序的字段
    public static final String ID="id";
    public static final String LIKE_NUM="like_num";
    public static final String REPLY_NUM="reply_num";
    public static final String CREATE_TIME="create_time";
    public static final String UPDATE_TIME="update_time";

    //排序的方式
    public static final String ASC="ASC";
    public static final String DESC="DESC";

    //条件
    private Integer articleId;
    private Integer userId;

    private String orderField;
    private String orderType=ASC;

    public ArticleCommentQuery(){}

    public ArticleCommentQuery(Integer articleId, Integer userId, String orderField, String orderType) {
        this.articleId = articleId;
        this.userId = userId;
        this.orderField = orderField;
        this.orderType = orderType;
    }

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
