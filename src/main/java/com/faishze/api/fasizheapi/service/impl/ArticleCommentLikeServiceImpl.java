package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.ArticleCommentLikeMapper;
import com.faishze.api.fasizheapi.pojo.do0.ArticleCommentLike;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentDTO;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentLikeDTO;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleCommentLikeService;
import com.faishze.api.fasizheapi.service.ArticleCommentService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author masonluo
 * @date 2019/10/27 11:29 PM
 */
@Service
public class ArticleCommentLikeServiceImpl implements ArticleCommentLikeService {

    @Autowired
    ArticleCommentLikeMapper articleCommentLikeMapper;

    @Autowired
    ArticleCommentService articleCommentService;

    @Autowired
    Mapper dozerMapper;

    @Override
    public Result saveArticleCommentLikeDTO(ArticleCommentLikeDTO articleCommentLikeDTO) {
        ArticleCommentLike articleCommentLike = dozerMapper.map(articleCommentLikeDTO, ArticleCommentLike.class);
        articleCommentLikeMapper.saveArticleCommentLike(articleCommentLike);
        articleCommentLikeDTO.setId(articleCommentLike.getId());
        //评论的点赞数+1
        Long articleCommentId=articleCommentLikeDTO.getCommentId();
        ArticleCommentDTO articleCommentDTO= (ArticleCommentDTO) articleCommentService.getArticleCommentDTO(articleCommentId).getData();
        articleCommentDTO.setLikeNum(articleCommentDTO.getLikeNum()+1);
        articleCommentService.updateArticleCommentDTO(articleCommentDTO);

        return Result.success(articleCommentLikeDTO);
    }

    @Override
    public Result deleteArticleCommentLikeDTO(Long id) {
        ArticleCommentLike articleCommentLike=articleCommentLikeMapper.getArticleCommentLike(id);
        articleCommentLikeMapper.deleteArticleCommentLike(id);
        //评论的点赞数-1
        Long articleCommentId=articleCommentLike.getCommentId();
        ArticleCommentDTO articleCommentDTO= (ArticleCommentDTO) articleCommentService.getArticleCommentDTO(articleCommentId).getData();
        articleCommentDTO.setLikeNum(articleCommentDTO.getLikeNum()-1);
        articleCommentService.updateArticleCommentDTO(articleCommentDTO);
        return Result.success();
    }

    @Override
    public Result getArticleCommentLikeDTOsByUserIdAndCommentId(Integer userId, Long commentId) {
        ArticleCommentLikeDTO articleCommentLikeDTO;
        ArticleCommentLike articleCommentLike =
                articleCommentLikeMapper.getArticleCommentLikeByUserIdAndCommentId(userId, commentId);
        if(articleCommentLike != null) {
            articleCommentLikeDTO=dozerMapper.map(articleCommentLike,ArticleCommentLikeDTO.class);
            return Result.success(articleCommentLikeDTO);
        }
        return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND,"未找到该评论点赞记录");
    }

}
