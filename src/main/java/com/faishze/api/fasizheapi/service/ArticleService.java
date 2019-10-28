package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.dao.query.ArticleQuery;
import com.faishze.api.fasizheapi.pojo.dto.ArticleDTO;
import com.github.pagehelper.Page;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/10/27
 */
public interface ArticleService {

    /**
     * @description: 传入文章，保存文章，返回保存成功的文章
     * @param articleDTO
     * @return: com.faishze.api.fasizheapi.pojo.dto.ArticleDTO
     * @author: 杜科
     * @date: 2019/10/27
     */
    ArticleDTO saveArticleDTO(ArticleDTO articleDTO);

    int deleteArticleDTO(Integer id);

    ArticleDTO getArticleDTO(Integer id);

    ArticleDTO updateArticleDTO(ArticleDTO articleDTO);

    Page<ArticleDTO> listArticleDTOs(Integer pageNum, Integer pageSize);

    /**
     * @description: 按查询条件分页返回相应文章
     * @param pageNum 当前页号
     * @param pageSize 页大小
     * @param articleQuery 查询条件
     * @return: com.github.pagehelper.Page<com.faishze.api.fasizheapi.pojo.dto.ArticleDTO>
     * @author: 杜科
     * @date: 2019/10/27
     */
    Page<ArticleDTO> listArticleDTOsByQuery(Integer pageNum, Integer pageSize, ArticleQuery articleQuery);

}
