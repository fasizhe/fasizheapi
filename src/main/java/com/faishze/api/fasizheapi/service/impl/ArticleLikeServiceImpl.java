package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.ArticleLikeMapper;
import com.faishze.api.fasizheapi.pojo.do0.ArticleLike;
import com.faishze.api.fasizheapi.pojo.dto.ArticleLikeDTO;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleLikeService;
import com.faishze.api.fasizheapi.service.ArticleService;
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
public class ArticleLikeServiceImpl implements ArticleLikeService {

    @Autowired
    ArticleLikeMapper articleLikeMapper;

    @Autowired
    ArticleService articleService;

    @Autowired
    Mapper dozerMapper;

    @Value("${redis.articleLike.key}")
    String preArticleLikeKey;

    @Value("${redis.articleLike.field}")
    String preArticleLikeField;

    @Override
    public Result saveArticleLikeDTO(ArticleLikeDTO articleLikeDTO) {
        Integer articleId = articleLikeDTO.getArticleId();
        ArticleLike articleLike = dozerMapper.map(articleLikeDTO, ArticleLike.class);
        articleLikeMapper.saveArticleLike(articleLike);
        //更新缓存
        String articleLikeKey =  preArticleLikeKey+ articleId;
        String articleLikeField = preArticleLikeField+articleLike.getUserId();
        JedisUtil.hsetField(articleLikeKey,articleLikeField,articleLike);
        //文章的点赞数+1
        articleService.riseLikeNum(articleId);
        System.out.println("成功点赞");

        articleLikeDTO.setId(articleLike.getId());
        return Result.success(articleLikeDTO);
    }

    @Override
    public Result deleteArticleLikeDTO(Long id) {
        ArticleLike articleLike = articleLikeMapper.getArticleLike(id);
        articleLikeMapper.deleteArticleLike(id);
        //更新缓存
        String articleLikeKey =  preArticleLikeKey+ articleLike.getArticleId();
        String articleLikeField = preArticleLikeField+articleLike.getUserId();
        JedisUtil.hdeleteField(articleLikeKey,articleLikeField);
        //文章的点赞数-1
        Integer articleId = articleLike.getArticleId();
        articleService.reduceLikeNum(articleId);
        System.out.println("成功取消点赞");
        return Result.success();
    }

    @Override
    public Result getArticleLikeDTOsByUserIdAndArticleId(Integer userId, Integer articleId) {
        ArticleLike articleLike = new ArticleLike();
        ArticleLikeDTO articleLikeDTO;
        String articleLikeKey =  preArticleLikeKey+ articleId;
        //30天内,long在1000*60*60*24*25越界
        Date deadTime=new Date(new Date().getTime()-1000*60*60*24*15-1000*60*60*24*15);

        //先在缓存中寻找，其中含有该篇文章一定时间内的点赞记录
        if (!JedisUtil.isExist(articleLikeKey)) {
            //从数据库中读出这篇文章一定时间（30天）内的点赞记录，并写到缓存
            List<ArticleLike> articleLikes = articleLikeMapper.listArticleLikesByArticleIdAndDeadTime(articleId,
                    deadTime);
            //设置无效数据，防止为空时下次再去读数据库
            if(articleLikes.isEmpty()) JedisUtil.hsetField(articleLikeKey,"null","null");
            else{
                for (ArticleLike like : articleLikes) {
                    String articleLikeField = preArticleLikeField+like.getUserId();
                    JedisUtil.hsetField(articleLikeKey,articleLikeField,like);
                }
            }
        }
        String articleLikeField = preArticleLikeField+userId;
        if (JedisUtil.ishFieldExist(articleLikeKey, articleLikeField)) {
            articleLike = (ArticleLike) JedisUtil.hgetField(articleLikeKey, articleLikeField, articleLike);
            articleLikeDTO = dozerMapper.map(articleLike, ArticleLikeDTO.class);
            System.out.println("已经点过赞了");
            return Result.success(articleLikeDTO);
        } else {
            articleLike = null;
            System.out.println("还没有点赞");
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "未找到该用户对该文章点赞记录");
        }
    }
}
