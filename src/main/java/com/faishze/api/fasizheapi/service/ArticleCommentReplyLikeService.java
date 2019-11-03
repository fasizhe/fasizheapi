package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentReplyLikeDTO;
import com.faishze.api.fasizheapi.result.Result;

/**
 * @author masonluo
 * @date 2019/10/27 5:10 PM
 */
public interface ArticleCommentReplyLikeService {

    Result saveArticleCommentReplyLikeDTO(ArticleCommentReplyLikeDTO articleCommentReplyLikeDTO);

    Result deleteArticleCommentReplyLikeDTO(Long Id);

    Result getArticleCommentReplyLikeDTOsByUserIdAndReplyId(Integer userId, Long replytId);
}
