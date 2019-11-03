package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.ArticleCollectionMapper;
import com.faishze.api.fasizheapi.pojo.do0.ArticleCollection;
import com.faishze.api.fasizheapi.pojo.do0.entity.AritcleCollectionEntity;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCollectionDTO;
import com.faishze.api.fasizheapi.pojo.dto.ArticleDTO;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleCollectionService;
import com.faishze.api.fasizheapi.service.ArticleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author masonluo
 * @date 2019/10/27 11:28 PM
 */
@Service
public class ArticleCollectionServiceImpl implements ArticleCollectionService {

    @Autowired
    ArticleCollectionMapper articleCollectionMapper;

    @Autowired
    ArticleService articleService;

    @Autowired
    Mapper dozerMapper;

    @Override
    public Result deleteArticleCollection(Long id) {
        ArticleCollection articleCollection=articleCollectionMapper.getArticleCollection(id);
        articleCollectionMapper.deleteArticleCollection(id);
        //文章的收藏数-1
        Integer articleId=articleCollection.getArticleId();
        ArticleDTO articleDTO= (ArticleDTO) articleService.getArticleDTO(articleId).getData();
        articleDTO.setCollectionNum(articleDTO.getCommentNum()-1);
        articleService.updateArticleDTO(articleDTO);

        return Result.success();
    }

    @Override
    public Result saveArticleCollectionDTO(ArticleCollectionDTO record) {
        ArticleCollection articleCollection = new ArticleCollection();
        articleCollection.setArticleId(record.getArticle().getId());
        articleCollection.setUserId(record.getUserId());
        articleCollectionMapper.saveArticleCollection(articleCollection);
        record.setId(articleCollection.getId());
        //文章的收藏数+1
        Integer articleId=articleCollection.getArticleId();
        ArticleDTO articleDTO= (ArticleDTO) articleService.getArticleDTO(articleId).getData();
        articleDTO.setCollectionNum(articleDTO.getCommentNum()+1);
        articleService.updateArticleDTO(articleDTO);

        return Result.success(record);
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
}
