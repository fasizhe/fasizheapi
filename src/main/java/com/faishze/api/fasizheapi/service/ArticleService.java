package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.pojo.dto.ArticleDTO;
import com.faishze.api.fasizheapi.query.ArticleQuery;
import com.faishze.api.fasizheapi.result.Result;

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
    Result saveArticleDTO(ArticleDTO articleDTO);

    Result deleteArticleDTO(Integer id);

    Result getArticleDTO(Integer id);

    Result updateArticleDTO(ArticleDTO articleDTO);

    Result listArticleDTOs(Integer pageNum, Integer pageSize);

    /**
     * @description: 按查询条件分页返回相应文章
     * @param pageNum 当前页号
     * @param pageSize 页大小
     * @param articleQuery 查询条件
     * @return: com.github.pagehelper.Page<com.faishze.api.fasizheapi.pojo.dto.ArticleDTO>
     * @author: 杜科
     * @date: 2019/10/27
     */
    Result listArticleDTOsByQuery(Integer pageNum, Integer pageSize, ArticleQuery articleQuery);

    //封禁文章
    Result banArticleDTO(Integer id);

    //增加文章浏览数
    Result riseViewNum(Integer id);

    //增加文章点赞数
    Result riseLikeNum(Integer id);

    //减少文章点赞数
    Result reduceLikeNum(Integer id);

    //增加文章评论数
    Result riseCommentNum(Integer id);

    //增加文章收藏数
    Result riseCollectionNum(Integer id);

    //减少文章收藏数
    Result reduceCollectionNum(Integer id);

}
