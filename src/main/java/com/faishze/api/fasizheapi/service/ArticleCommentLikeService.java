package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentLikeDTO;
import com.faishze.api.fasizheapi.result.Result;

/**
 * @author masonluo
 * @date 2019/10/27 5:10 PM
 */
public interface ArticleCommentLikeService {

    Result saveArticleCommentLikeDTO(ArticleCommentLikeDTO articleCommentLikeDTO);

    Result deleteArticleCommentLikeDTO(Long Id);

    Result getArticleCommentLikeDTOsByUserIdAndCommentId(Integer userId, Long commentId);
}
