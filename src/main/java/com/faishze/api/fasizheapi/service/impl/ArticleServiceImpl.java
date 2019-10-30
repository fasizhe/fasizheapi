package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.ArticleMapper;
import com.faishze.api.fasizheapi.query.ArticleQuery;
import com.faishze.api.fasizheapi.pojo.do0.Article;
import com.faishze.api.fasizheapi.pojo.dto.ArticleDTO;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleService;
import com.faishze.api.fasizheapi.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/10/27
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    Mapper dozerMapper;

    @Autowired
    UserService userService;

    @Override
    public Result saveArticleDTO(ArticleDTO articleDTO) {
        //前期设定
        articleDTO.setAvailable(true);

        articleDTO.setViewNum((long) 0);
        articleDTO.setLikeNum(0);
        articleDTO.setCommentNum(0);
        articleDTO.setCollectionNum(0);
        Article article;
        article=dozerMapper.map(articleDTO,Article.class);
        articleMapper.saveArticle(article);
        articleDTO.setId(article.getId());
        Result result=Result.success(articleDTO);
        return result;
    }

    @Override
    public int deleteArticleDTO(Integer id) {
        return articleMapper.deleteArticle(id);
    }

    @Override
    public Result getArticleDTO(Integer id) {
        ArticleDTO articleDTO;
        Article article=articleMapper.getArticle(id);
        //DO转DTO
        articleDTO=dozerMapper.map(article,ArticleDTO.class);
        Result result=Result.success(articleDTO);
        return result ;
    }

    @Override
    public Result updateArticleDTO(ArticleDTO articleDTO) {
        Article article;
        article=dozerMapper.map(articleDTO,Article.class);
        articleMapper.updateArticle(article);
        Result result=Result.success(articleDTO);
        return result ;
    }

    @Override
    public Result listArticleDTOs(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        Page<Article> articles=articleMapper.listArticles();
        Page<ArticleDTO> articleDTOS=new Page<>();
        for (Article article : articles) {
            articleDTOS.add(dozerMapper.map(article,ArticleDTO.class));
        }
        Result result=Result.success(articleDTOS);
        return result ;
    }

    @Override
    public Result listArticleDTOsByQuery(Integer pageNum, Integer pageSize, ArticleQuery articleQuery) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Article> articles=articleMapper.listArticlesByQuery(articleQuery);
        Page<ArticleDTO> articleDTOS=new Page<>();
        for (Article article : articles) {
            articleDTOS.add(dozerMapper.map(article,ArticleDTO.class));
        }
        Result result=Result.success(articleDTOS);
        return result ;
    }

//    public Page<ArticleDTO> listArticleDTOsByAvailableOderByLikeNumDESC(Integer pageNum, Integer pageSize){
//        ArticleQuery articleQuery=new ArticleQuery(true,null,null,null,ArticleQuery.LIKE_NUM,ArticleQuery.DESC);
//        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
//    }
//
//    //按收藏数降序，分页返回可用的文章
//    public Page<ArticleDTO> listArticleDTOsByAvailableOderByCollentionNumDESC(Integer pageNum, Integer pageSize){
//        ArticleQuery articleQuery=new ArticleQuery(true,null,null,null,ArticleQuery.COLLECTION_NUM,ArticleQuery.DESC);
//        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
//    }
//
//    //按浏览数降序，分页返回可用的文章
//    public Page<ArticleDTO> listArticleDTOsByAvailableOderByViewNumDESC(Integer pageNum, Integer pageSize){
//        ArticleQuery articleQuery=new ArticleQuery(true,null,null,null,ArticleQuery.VIEW_NUM,ArticleQuery.DESC);
//        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
//    }
//
//    //按评论数降序，分页返回可用的文章
//    public Page<ArticleDTO> listArticleDTOsByAvailableOderByCommentNumDESC(Integer pageNum, Integer pageSize){
//        ArticleQuery articleQuery=new ArticleQuery(true,null,null,null,ArticleQuery.COMMENT_NUM,ArticleQuery.DESC);
//        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
//    }
//
//    //按更新时间降序，分页返回可用的文章
//    public Page<ArticleDTO> listArticleDTOsByAvailableOderByUpateTimeDESC(Integer pageNum, Integer pageSize){
//        ArticleQuery articleQuery=new ArticleQuery(true,null,null,null,ArticleQuery.UPDATE_TIME,ArticleQuery.DESC);
//        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
//    }
//
//    //按类型，收藏数降序，分页返回可用的文章
//    public Page<ArticleDTO> listArticleDTOsByAvailableAndTypeOderByCollectionDESC(Integer pageNum, Integer pageSize, ArticleType articleType){
//        ArticleQuery articleQuery=new ArticleQuery(true,null,null,articleType,ArticleQuery.COLLECTION_NUM,ArticleQuery.DESC);
//        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
//    }
//
//    //按类型，点赞数降序，分页返回可用的文章
//    public Page<ArticleDTO> listArticleDTOsByAvailableAndTypeOderByLikeNumDESC(Integer pageNum, Integer pageSize, ArticleType articleType){
//        ArticleQuery articleQuery=new ArticleQuery(true,null,null,articleType,ArticleQuery.LIKE_NUM,ArticleQuery.DESC);
//        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
//    }
//
//    //按类型，浏览数降序，分页返回可用的文章
//    public Page<ArticleDTO> listArticleDTOsByAvailableAndTypeOderByViewNumDESC(Integer pageNum, Integer pageSize, ArticleType articleType){
//        ArticleQuery articleQuery=new ArticleQuery(true,null,null,articleType,ArticleQuery.VIEW_NUM,ArticleQuery.DESC);
//        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
//    }
//
//    //按类型，评论数降序，分页返回可用的文章
//    public Page<ArticleDTO> listArticleDTOsByAvailableAndTypeOderByCommentNumDESC(Integer pageNum, Integer pageSize, ArticleType articleType){
//        ArticleQuery articleQuery=new ArticleQuery(true,null,null,articleType,ArticleQuery.COMMENT_NUM,ArticleQuery.DESC);
//        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
//    }
//
//    //按类型，更新时间降序，分页返回可用的文章
//    public Page<ArticleDTO> listArticleDTOsByAvailableAndTypeOderByUpateTimeDESC(Integer pageNum, Integer pageSize, ArticleType articleType){
//        ArticleQuery articleQuery=new ArticleQuery(true,null,null,articleType,ArticleQuery.UPDATE_TIME,ArticleQuery.DESC);
//        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
//    }


}
