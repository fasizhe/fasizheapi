package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.dao.query.ArticleCommentQuery;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentDTO;
import com.github.pagehelper.Page;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/10/27
 */
public interface ArticleCommentService {

    ArticleCommentDTO saveArticleCommentDTO(ArticleCommentDTO record);

    int deleteArticleCommentDTO(Long id);

    ArticleCommentDTO getArticleCommentDTO(Long id);

    ArticleCommentDTO updateArticleCommentDTO(ArticleCommentDTO record);

    Page<ArticleCommentDTO> listArticleCommentDTOs(Integer pageNum, Integer pageSize);

    Page<ArticleCommentDTO> listArticleCommentDTOsByQuery(Integer pageNum, Integer pageSize,
                                                          ArticleCommentQuery articleCommentQuery);
}
