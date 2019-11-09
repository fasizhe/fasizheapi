package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.ArticleCommentReplyLikeMapper;
import com.faishze.api.fasizheapi.pojo.do0.ArticleCommentReplyLike;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentReplyLikeDTO;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleCommentReplyLikeService;
import com.faishze.api.fasizheapi.service.ArticleCommentReplyService;
import com.faishze.api.fasizheapi.util.JedisUtil;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author masonluo
 * @date 2019/10/27 11:28 PM
 */
@Service
@Transactional
public class ArticleCommentReplyLikeServiceImpl implements ArticleCommentReplyLikeService {

    @Autowired
    ArticleCommentReplyLikeMapper articleCommentReplyLikeMapper;

    @Autowired
    ArticleCommentReplyService articleCommentReplyService;

    @Autowired
    Mapper dozerMapper;

    @Value("${redis.replyLike.key}")
    String preArticleCommentReplyLikeKey;

    @Value("${redis.replyLike.field}")
    String preArticleCommentReplyLikeField;

    @Override
    public Result saveArticleCommentReplyLikeDTO(ArticleCommentReplyLikeDTO articleCommentReplyLikeDTO) {
        ArticleCommentReplyLike articleCommentReplyLike = dozerMapper.map(articleCommentReplyLikeDTO,
                ArticleCommentReplyLike.class);
        articleCommentReplyLikeMapper.saveArticleCommentReplyLike(articleCommentReplyLike);
        //更新缓存
        String likeKey =  preArticleCommentReplyLikeKey+ articleCommentReplyLike.getReplyId();
        String likeField = preArticleCommentReplyLikeField+articleCommentReplyLike.getUserId();
        JedisUtil.hsetField(likeKey,likeField,articleCommentReplyLike);
        //回复的点赞数+1
        Long articleCommentReplyId = articleCommentReplyLikeDTO.getReplyId();
        articleCommentReplyService.riseLikeNum(articleCommentReplyId);

        articleCommentReplyLikeDTO.setId(articleCommentReplyLike.getId());
        return Result.success(articleCommentReplyLikeDTO);
    }

    @Override
    public Result deleteArticleCommentReplyLikeDTO(Long id) {
        ArticleCommentReplyLike articleCommentReplyLike = articleCommentReplyLikeMapper.getArticleCommentReplyLike(id);
        articleCommentReplyLikeMapper.deleteArticleCommentReplyLike(id);
        //更新缓存
        String likeKey =  preArticleCommentReplyLikeKey+ articleCommentReplyLike.getReplyId();
        String likeField = preArticleCommentReplyLikeField+articleCommentReplyLike.getUserId();
        JedisUtil.hdeleteField(likeKey,likeField);
        //回复的点赞数-1
        Long articleCommentReplyId = articleCommentReplyLike.getReplyId();
        articleCommentReplyService.reduceLikeNum(articleCommentReplyId);
        return Result.success();
    }

    @Override
    public Result getArticleCommentReplyLikeDTOsByUserIdAndReplyId(Integer userId, Long replyId) {
        ArticleCommentReplyLike articleCommentReplyLike = new ArticleCommentReplyLike();
        ArticleCommentReplyLikeDTO articleCommentReplyLikeDTO;
        String articleCommentReplyLikeKey =  preArticleCommentReplyLikeKey+ replyId;
        //30天内,long在1000*60*60*24*25越界
        Date deadTime=new Date(new Date().getTime()-1000*60*60*24*15-1000*60*60*24*15);

        //先在缓存中寻找，其中含有该回复一定时间内的点赞记录
        if (!JedisUtil.isExist(articleCommentReplyLikeKey)) {
            //从数据库中读出这回复一定时间（30天）内的点赞记录，并写到缓存
            List<ArticleCommentReplyLike> articleCommentReplyLikes = articleCommentReplyLikeMapper.listArticleCommentReplyLikesByReplyIdAndDeadTime(replyId,deadTime);
            //设置无效数据，防止为空时下次再去读数据库
            if(articleCommentReplyLikes.isEmpty()) JedisUtil.hsetField(articleCommentReplyLikeKey,"null","null");
            else{
                for (ArticleCommentReplyLike like : articleCommentReplyLikes) {
                    String articleCommentReplyLikeField = preArticleCommentReplyLikeField+like.getUserId();
                    JedisUtil.hsetField(articleCommentReplyLikeKey,articleCommentReplyLikeField,like);
                }
            }
        }
        String articleCommentReplyLikeField = preArticleCommentReplyLikeField+userId;
        if (JedisUtil.ishFieldExist(articleCommentReplyLikeKey, articleCommentReplyLikeField)) {
            articleCommentReplyLike = (ArticleCommentReplyLike) JedisUtil.hgetField(articleCommentReplyLikeKey, articleCommentReplyLikeField, articleCommentReplyLike);
            articleCommentReplyLikeDTO = dozerMapper.map(articleCommentReplyLike, ArticleCommentReplyLikeDTO.class);
            System.out.println("已经点过赞了");
            return Result.success(articleCommentReplyLikeDTO);
        } else {
            articleCommentReplyLike = null;
            System.out.println("还没有点赞");
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "未找到该用户对该回复点赞记录");
        }
    }
}
