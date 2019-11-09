package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.ArticleCollectionMapper;
import com.faishze.api.fasizheapi.pojo.do0.ArticleCollection;
import com.faishze.api.fasizheapi.pojo.do0.entity.AritcleCollectionEntity;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCollectionDTO;
import com.faishze.api.fasizheapi.result.ErrorCode;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleCollectionService;
import com.faishze.api.fasizheapi.service.ArticleService;
import com.faishze.api.fasizheapi.util.JedisUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author masonluo
 * @date 2019/10/27 11:28 PM
 */
@Service
@Transactional
public class ArticleCollectionServiceImpl implements ArticleCollectionService {

    @Autowired
    ArticleCollectionMapper articleCollectionMapper;

    @Autowired
    ArticleService articleService;

    @Autowired
    Mapper dozerMapper;

    @Value("${redis.articleCollection.key}")
    String preArticleCollectionKey;

    @Value("${redis.articleCollection.field}")
    String preArticleCollectionField;

    @Override
    public Result saveArticleCollectionDTO(ArticleCollectionDTO record) {
        Integer articleId=record.getArticle().getId();
        ArticleCollection articleCollection = new ArticleCollection();
        articleCollection.setArticleId(articleId);
        articleCollection.setUserId(record.getUserId());
        articleCollectionMapper.saveArticleCollection(articleCollection);
        //更新缓存
        String articleCollectionKey =  preArticleCollectionKey+ articleId;
        String articleCollectionField = preArticleCollectionField+articleCollection.getUserId();
        JedisUtil.hsetField(articleCollectionKey,articleCollectionField,articleCollection);
        //文章的收藏数+1
        articleService.riseCollectionNum(articleId);
        System.out.println("成功收藏");
        
        record.setId(articleCollection.getId());
        return Result.success(record);
    }

    @Override
    public Result deleteArticleCollection(Long id) {
        ArticleCollection articleCollection=articleCollectionMapper.getArticleCollection(id);
        Integer articleId=articleCollection.getArticleId();
        articleCollectionMapper.deleteArticleCollection(id);
        //更新缓存
        String articleCollectionKey =  preArticleCollectionKey+ articleId;
        String articleCollectionField = preArticleCollectionField+articleCollection.getUserId();
        JedisUtil.hdeleteField(articleCollectionKey,articleCollectionField);
        //文章的收藏数-1
        articleService.reduceCollectionNum(articleId);
        System.out.println("成功取消收藏");
        return Result.success();
    }

    @Override
    public Result getArticleCollectionDTO(Long id) {
        return null;
    }

    @Override
    public Result listArticleCollectionDTOs() {
        return null;
    }

    @Override
    public Result updatetArticleCollectionDTO(ArticleCollectionDTO record) {
        return null;
    }

    @Override
    public Result listArticleCollectionDTOsByCollectorId(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<AritcleCollectionEntity> articleCollectionEntitys =
                articleCollectionMapper.listArticleCollectionEntitysByCollectorId(userId);
        Page<ArticleCollectionDTO> articleCollectionDTOS=new Page<>();
        ArticleCollectionDTO articleCollectionDTO;
        for (AritcleCollectionEntity articleCollectionEntity : articleCollectionEntitys) {
            articleCollectionDTO=dozerMapper.map(articleCollectionEntity,ArticleCollectionDTO.class);
            articleCollectionDTOS.add(articleCollectionDTO);
        }
        return Result.success(articleCollectionDTOS);
    }

    @Override
    public Result getArticleCollectionDTOsByUserIdAndArticleId(Integer userId, Integer articleId) {
        ArticleCollection articleCollection = new ArticleCollection();
        ArticleCollectionDTO articleCollectionDTO;
        //一个人的收藏比较少
        String articleCollectionKey =  preArticleCollectionKey+ userId;
        //先在缓存中寻找
        if (!JedisUtil.isExist(articleCollectionKey)) {
            //从数据库中读出该用户所有的收藏记录，并写到缓存
            List<ArticleCollection> articleCollections = articleCollectionMapper.listArticleCollectionsByCollectorId(userId);
            //设置无效数据，防止为空时下次再去读数据库
            if(articleCollections.isEmpty()) JedisUtil.hsetField(preArticleCollectionKey,"null","null");
            else {
                for (ArticleCollection Collection : articleCollections) {
                    String articleCollectionField = preArticleCollectionField+Collection.getArticleId();
                    JedisUtil.hsetField(articleCollectionKey,articleCollectionField,Collection);
                }
            }
        }
        String articleCollectionField = preArticleCollectionField+articleId;
        if (JedisUtil.ishFieldExist(articleCollectionKey, articleCollectionField)) {
            articleCollection = (ArticleCollection) JedisUtil.hgetField(articleCollectionKey, articleCollectionField, articleCollection);
            articleCollectionDTO = dozerMapper.map(articleCollection, ArticleCollectionDTO.class);
            System.out.println("已经收藏过了");
            return Result.success(articleCollectionDTO);
        } else {
            articleCollection = null;
            System.out.println("还没有收藏");
            return Result.fail(ErrorCode.INVALID_PARAMETER_NOT_FOUND, "未找到该用户对该文章收藏记录");
        }
    }
}
