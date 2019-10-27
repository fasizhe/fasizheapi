package com.faishze.api.fasizheapi.dao.query;

import com.faishze.api.fasizheapi.constant.ArticleType;

/**
 * @author 杜科
 * @description 查询文章的条件
 * @contact 15521177704
 * @since 2019/10/24
 */
public class ArticleQuery {

    //要排序的字段
    public static final String ID="id";
    public static final String LIKE_NUM="like_num";
    public static final String COLLECTION_NUM="collection_num";
    public static final String VIEW_NUM="view_num";
    public static final String COMMENT_NUM="comment_num";
    public static final String CREATE_TIME="create_time";
    public static final String UPDATE_TIME="update_time";

    //排序的方式
    public static final String ASC="ASC";
    public static final String DESC="DESC";

    //条件
    private Boolean available;
    private Integer userId;
    private ArticleType type;
    private String orderField;
    private String orderType=ASC;

    public ArticleQuery() {
    }

    public ArticleQuery(Boolean available, Integer userId, ArticleType type, String orderField, String orderType) {
        this.available = available;
        this.userId = userId;
        this.type = type;
        this.orderField = orderField;
        this.orderType = orderType;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
        this.type = type;
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
