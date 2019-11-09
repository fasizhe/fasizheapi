package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.ArticleCommentLikeMapper;
import com.faishze.api.fasizheapi.pojo.do0.ArticleCommentLike;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentLikeDTO;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleCommentLikeService;
import com.faishze.api.fasizheapi.service.ArticleCommentService;
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
 * @date 2019/10/27 11:29 PM
 */
@Service
@Transactional
public class ArticleCommentLikeServiceImpl implements ArticleCommentLikeService {

    @Autowired
    ArticleCommentLikeMapper articleCommentLikeMapper;

    @Autowired
    ArticleCommentService articleCommentService;

    @Autowired
    Mapper dozerMapper;

    @Value("${redis.commentLike.key}")
    String preArticleCommentLikeKey;

    @Value("${redis.commentLike.field}")
    String preArticleCommentLikeField;

    @Override
    public Result saveArticleCommentLikeDTO(ArticleCommentLikeDTO articleCommentLikeDTO) {
        ArticleCommentLike articleCommentLike = dozerMapper.map(articleCommentLikeDTO, ArticleCommentLike.class);
        articleCommentLikeMapper.saveArticleCommentLike(articleCommentLike);
        //更新缓存
        String likeKey =  preArticleCommentLikeKey+ articleCommentLike.getCommentId();
        String likeField = preArticleCommentLikeField+articleCommentLike.getUserId();
        JedisUtil.hsetField(likeKey,likeField,articleCommentLike);
        //评论的点赞数+1
        articleCommentService.riseLikeNum(articleCommentLikeDTO.getCommentId());
        articleCommentLikeDTO.setId(articleCommentLike.getId());
        return Result.success(articleCommentLikeDTO);
    }

    @Override
    public Result deleteArticleCommentLikeDTO(Long id) {
        ArticleCommentLike articleCommentLike=articleCommentLikeMapper.getArticleCommentLike(id);
        articleCommentLikeMapper.deleteArticleCommentLike(id);
        //更新缓存
        String likeKey =  preArticleCommentLikeKey+ articleCommentLike.getCommentId();
        String likeField = preArticleCommentLikeField+articleCommentLike.getUserId();
        JedisUtil.hdeleteField(likeKey,likeField);
        //评论的点赞数-1
        articleCommentService.reduceLikeNum(articleCommentLike.getCommentId());
        return Result.success();
    }

    @Override
    public Result getArticleCommentLikeDTOsByUserIdAndCommentId(Integer userId, Long commentId) {
        ArticleCommentLike articleCommentLike = new ArticleCommentLike();
        ArticleCommentLikeDTO articleCommentLikeDTO;
        String articleCommentLikeKey =  preArticleCommentLikeKey+ commentId;
        //30天内,long在1000*60*60*24*25越界
        Date deadTime=new Date(new Date().getTime()-1000*60*60*24*15-1000*60*60*24*15);

        //先在缓存中寻找，其中含有该评论一定时间内的点赞记录
        if (!JedisUtil.isExist(articleCommentLikeKey)) {
            //从数据库中读出这评论一定时间（30天）内的点赞记录，并写到缓存
            List<ArticleCommentLike> articleCommentLikes = articleCommentLikeMapper.listArticleCommentLikesByCommentIdAndDeadTime(commentId,
                    deadTime);
            //设置无效数据，防止为空时下次再去读数据库
            if(articleCommentLikes.isEmpty()) JedisUtil.hsetField(articleCommentLikeKey,"null","null");
            else {
                for (ArticleCommentLike like : articleCommentLikes) {
                    String articleCommentLikeField = preArticleCommentLikeField+like.getUserId();
                    JedisUtil.hsetField(articleCommentLikeKey,articleCommentLikeField,like);
                }
            }
        }
        String articleCommentLikeField = preArticleCommentLikeField+userId;
        if (JedisUtil.ishFieldExist(articleCommentLikeKey, articleCommentLikeField)) {
            articleCommentLike = (ArticleCommentLike) JedisUtil.hgetField(articleCommentLikeKey, articleCommentLikeField, articleCommentLike);
            articleCommentLikeDTO = dozerMapper.map(articleCommentLike, ArticleCommentLikeDTO.class);
            System.out.println("已经点过赞了");
            return Result.success(articleCommentLikeDTO);
        } else {
            articleCommentLike = null;
            System.out.println("还没有点赞");
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "未找到该用户对该评论点赞记录");
        }
    }

}
