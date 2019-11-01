package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentReplyDTO;
import com.faishze.api.fasizheapi.query.ArticleCommentReplyQuery;
import com.faishze.api.fasizheapi.result.Result;

/**
 * @author masonluo
 * @date 2019/10/27 5:10 PM
 */
public interface ArticleCommentReplyService {

    Result saveArticleCommentReplyDTO(ArticleCommentReplyDTO record);

    Result deleteArticleCommentReplyDTO(Long id);

    Result getArticleCommentReplyDTO(Long id);

    Result updateArticleCommentReplyDTO(ArticleCommentReplyDTO record);

    Result listArticleCommentReplyDTOs(Integer pageNum, Integer pageSize);

    Result listArticleCommentReplyDTOsByQuery(Integer pageNum, Integer pageSize,
                                         ArticleCommentReplyQuery articleCommentReplyQuery);

    //删除回复
    Result clearArticleCommentReplyDTO(Long id);
}
