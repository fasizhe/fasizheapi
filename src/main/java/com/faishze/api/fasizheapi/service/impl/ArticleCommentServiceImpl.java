package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.query.ArticleCommentQuery;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentDTO;
import com.faishze.api.fasizheapi.service.ArticleCommentService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/10/27
 */
@Service
@Transactional
public class ArticleCommentServiceImpl implements ArticleCommentService {


    @Override
    public ArticleCommentDTO saveArticleCommentDTO(ArticleCommentDTO record) {
        return null;
    }

    @Override
    public int deleteArticleCommentDTO(Long id) {
        return 0;
    }

    @Override
    public ArticleCommentDTO getArticleCommentDTO(Long id) {
        return null;
    }

    @Override
    public ArticleCommentDTO updateArticleCommentDTO(ArticleCommentDTO record) {
        return null;
    }

    @Override
    public Page<ArticleCommentDTO> listArticleCommentDTOs(Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public Page<ArticleCommentDTO> listArticleCommentDTOsByQuery(Integer pageNum, Integer pageSize,
                                                                 ArticleCommentQuery articleCommentQuery) {
        return null;
    }
}
