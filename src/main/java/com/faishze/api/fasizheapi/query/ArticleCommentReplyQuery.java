package com.faishze.api.fasizheapi.query;

/**
 * @author 杜科
 * @description 文章评论回复查询条件
 * @contact 15521177704
 * @since 2019/10/26
 */
public class ArticleCommentReplyQuery {

    //条件
    private Integer articleId;
    private Long commentId;
    private Integer userId;
    private String userNickName;

    private String orderField;
    private String orderType="ASC";

    public ArticleCommentReplyQuery(){}

    public ArticleCommentReplyQuery(Integer articleId, Long commentId, Integer userId, String userNickName,
                                    String orderField, String orderType) {
        this.articleId = articleId;
        this.commentId = commentId;
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

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
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
