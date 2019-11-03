package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.ArticleCommentReplyLikeMapper;
import com.faishze.api.fasizheapi.pojo.do0.ArticleCommentReplyLike;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentReplyDTO;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentReplyLikeDTO;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleCommentReplyLikeService;
import com.faishze.api.fasizheapi.service.ArticleCommentReplyService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author masonluo
 * @date 2019/10/27 11:28 PM
 */
@Service
public class ArticleCommentReplyLikeServiceImpl implements ArticleCommentReplyLikeService {

    @Autowired
    ArticleCommentReplyLikeMapper articleCommentReplyLikeMapper;

    @Autowired
    ArticleCommentReplyService articleCommentReplyService;

    @Autowired
    Mapper dozerMapper;

    @Override
    public Result saveArticleCommentReplyLikeDTO(ArticleCommentReplyLikeDTO articleCommentReplyLikeDTO) {
        ArticleCommentReplyLike articleCommentReplyLike = dozerMapper.map(articleCommentReplyLikeDTO,
                ArticleCommentReplyLike.class);
        articleCommentReplyLikeMapper.saveArticleCommentReplyLike(articleCommentReplyLike);
        articleCommentReplyLikeDTO.setId(articleCommentReplyLike.getId());
        //回复的点赞数+1
        Long articleCommentReplyId = articleCommentReplyLikeDTO.getReplyId();
        ArticleCommentReplyDTO articleCommentReplyDTO =
                (ArticleCommentReplyDTO) articleCommentReplyService.getArticleCommentReplyDTO(articleCommentReplyId).getData();
        articleCommentReplyDTO.setLikeNum(articleCommentReplyDTO.getLikeNum() + 1);
        articleCommentReplyService.updateArticleCommentReplyDTO(articleCommentReplyDTO);

        return Result.success(articleCommentReplyLikeDTO);
    }

    @Override
    public Result deleteArticleCommentReplyLikeDTO(Long id) {
        ArticleCommentReplyLike articleCommentReplyLike = articleCommentReplyLikeMapper.getArticleCommentReplyLike(id);
        articleCommentReplyLikeMapper.deleteArticleCommentReplyLike(id);
        //回复的点赞数-1
        Long articleCommentReplyId = articleCommentReplyLike.getReplyId();
        ArticleCommentReplyDTO articleCommentReplyDTO =
                (ArticleCommentReplyDTO) articleCommentReplyService.getArticleCommentReplyDTO(articleCommentReplyId).getData();
        articleCommentReplyDTO.setLikeNum(articleCommentReplyDTO.getLikeNum() - 1);
        articleCommentReplyService.updateArticleCommentReplyDTO(articleCommentReplyDTO);
        return Result.success();
    }

    @Override
    public Result getArticleCommentReplyLikeDTOsByUserIdAndReplyId(Integer userId, Long replyId) {
        ArticleCommentReplyLikeDTO articleCommentReplyLikeDTO;
        ArticleCommentReplyLike articleCommentReplyLike =
                articleCommentReplyLikeMapper.getArticleCommentReplyLikeByUserIdAndReplyId(userId, replyId);
        if (articleCommentReplyLike != null) {
            articleCommentReplyLikeDTO = dozerMapper.map(articleCommentReplyLike, ArticleCommentReplyLikeDTO.class);
            return Result.success(articleCommentReplyLikeDTO);
        }
        return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "未找到该回复点赞记录");
    }
}
