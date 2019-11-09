package com.faishze.api.fasizheapi.converter;

import com.faishze.api.fasizheapi.constant.ArticleType;
import com.faishze.api.fasizheapi.pojo.do0.Article;

import java.util.Date;
import java.util.Map;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/11/7
 */
public class MapToObject {

    public static boolean map2Article(Map<String,String> map, Article article){
        ArticleType type=ArticleType.getArticleTypeByName(map.get("type"));
        article.setType(type);
        article.setId(Integer.valueOf(map.get("id")));
        article.setViewNum( Long.valueOf(map.get("viewNum")));
        article.setAvailable(Boolean.valueOf( map.get("available")));
        article.setUpdateTime(new Date(map.get("updateTime")));
        article.setCollectionNum(Integer.valueOf( map.get("collectionNum")));
        article.setCommentNum(Integer.valueOf(map.get("commentNum")));
        article.setLikeNum(Integer.valueOf(map.get("likeNum")));
        article.setCreateTime(new Date(map.get("createTime")));
        article.setTitle(map.get("title"));
        article.setContent( map.get("content"));
        article.setUserId(Integer.valueOf(map.get("userId")));
        article.setUserNickName(map.get("userNickName"));
        return true;
    }
}
