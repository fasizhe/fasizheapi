package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.pojo.dto.ArticleLikeDTO;
import com.faishze.api.fasizheapi.result.Result;

/**
 * @author masonluo
 * @date 2019/10/27 5:10 PM
 */
public interface ArticleLikeService {

    Result saveArticleLikeDTO(ArticleLikeDTO articleLikeDTO);

    Result deleteArticleLikeDTO(Long Id);

    //用于判断该用户是否点赞过该文章
    Result getArticleLikeDTOsByUserIdAndArticleId(Integer userId, Integer articleId);
}
