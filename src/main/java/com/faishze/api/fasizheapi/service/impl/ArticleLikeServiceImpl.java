package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.ArticleLikeMapper;
import com.faishze.api.fasizheapi.pojo.do0.ArticleLike;
import com.faishze.api.fasizheapi.pojo.dto.ArticleDTO;
import com.faishze.api.fasizheapi.pojo.dto.ArticleLikeDTO;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleLikeService;
import com.faishze.api.fasizheapi.service.ArticleService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author masonluo
 * @date 2019/10/27 11:28 PM
 */
@Service
public class ArticleLikeServiceImpl implements ArticleLikeService {
    
    @Autowired
    ArticleLikeMapper articleLikeMapper;

    @Autowired
    ArticleService articleService;

    @Autowired
    Mapper dozerMapper;

    @Override
    public Result saveArticleLikeDTO(ArticleLikeDTO articleLikeDTO) {
        ArticleLike articleLike = dozerMapper.map(articleLikeDTO, ArticleLike.class);
        articleLikeMapper.saveArticleLike(articleLike);
        articleLikeDTO.setId(articleLike.getId());
        //文章的点赞数+1
        Integer articleId=articleLikeDTO.getArticleId();
        ArticleDTO articleDTO= (ArticleDTO) articleService.getArticleDTO(articleId).getData();
        articleDTO.setLikeNum(articleDTO.getLikeNum()+1);
        articleService.updateArticleDTO(articleDTO);

        return Result.success(articleLikeDTO);
    }

    @Override
    public Result deleteArticleLikeDTO(Long id) {
        ArticleLike articleLike=articleLikeMapper.getArticleLike(id);
        articleLikeMapper.deleteArticleLike(id);
        //文章的点赞数-1
        Integer articleId=articleLike.getArticleId();
        ArticleDTO articleDTO= (ArticleDTO) articleService.getArticleDTO(articleId).getData();
        articleDTO.setLikeNum(articleDTO.getLikeNum()-1);
        articleService.updateArticleDTO(articleDTO);
        return Result.success();
    }

    @Override
    public Result getArticleLikeDTOsByUserIdAndArticleId(Integer userId, Integer articleId) {
        ArticleLikeDTO articleLikeDTO;
        ArticleLike articleLike =
                articleLikeMapper.getArticleLikeByUserIdAndArticleId(userId, articleId);
        if(articleLike != null) {
            articleLikeDTO=dozerMapper.map(articleLike,ArticleLikeDTO.class);
            return Result.success(articleLikeDTO);
        }
        return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND,"未找到该文章点赞记录");
    }
}
