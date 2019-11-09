package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.pojo.dto.ArticleCollectionDTO;
import com.faishze.api.fasizheapi.result.Result;

/**
 * @author masonluo
 * @date 2019/10/27 5:10 PM
 */
public interface ArticleCollectionService {
    Result deleteArticleCollection(Long id);

    Result saveArticleCollectionDTO(ArticleCollectionDTO record);

    Result getArticleCollectionDTO(Long id);

    Result listArticleCollectionDTOs();

    Result updatetArticleCollectionDTO(ArticleCollectionDTO record);

    //返回收藏者收藏的文章，id降序即收藏时间降序，由新到旧
    Result listArticleCollectionDTOsByCollectorId(Integer userId,Integer pageNume,Integer pageSize);

    //判断该用户是否收藏过该文章
    Result getArticleCollectionDTOsByUserIdAndArticleId(Integer userId,Integer articleId);
}
