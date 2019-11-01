package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentDTO;
import com.faishze.api.fasizheapi.query.ArticleCommentQuery;
import com.faishze.api.fasizheapi.result.Result;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/10/27
 */
public interface ArticleCommentService {

    Result saveArticleCommentDTO(ArticleCommentDTO record);

    Result deleteArticleCommentDTO(Long id);

    Result getArticleCommentDTO(Long id);

    Result updateArticleCommentDTO(ArticleCommentDTO record);

    Result listArticleCommentDTOs(Integer pageNum, Integer pageSize);

    Result listArticleCommentDTOsByQuery(Integer pageNum, Integer pageSize,
                                                          ArticleCommentQuery articleCommentQuery);

    //删除评论
    Result clearArticleCommentDTO(Long id);
}
