package com.faishze.api.fasizheapi.dao.query;

import com.faishze.api.fasizheapi.constant.ArticleType;

/**
 * @author 杜科
 * @description 查询文章的条件
 * @contact 15521177704
 * @since 2019/10/24
 */
public class ArticleQuery {

    public static final String LIKE_NUM="like_num";
    public static final String COLLECTION_NUM="collection_num";
    public static final String VIEW_NUM="view_num";
    public static final String COMMENT_NUM="comment_num";
    public static final String ASC="ASC";
    public static final String DESC="DESC";
    private Integer userId;
    private ArticleType type;
    private String orderField;
    private String orderType;

}
