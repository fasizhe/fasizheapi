package com.faishze.api.fasizheapi.query;

import com.faishze.api.fasizheapi.constant.ArticleType;

/**
 * @author 杜科
 * @description 查询文章的条件
 * @contact 15521177704
 * @since 2019/10/24
 */
public class ArticleQuery {

    //条件
    private Boolean available;
    private Integer userId;
    private String userNickName;
    private ArticleType type;

    private String orderField;
    private String orderType="ASC";

    public ArticleQuery() {
    }

    public ArticleQuery(Boolean available, Integer userId, String userNickName, ArticleType type, String orderField,
                        String orderType) {
        this.available = available;
        this.userId = userId;
        this.userNickName = userNickName;
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

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
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
