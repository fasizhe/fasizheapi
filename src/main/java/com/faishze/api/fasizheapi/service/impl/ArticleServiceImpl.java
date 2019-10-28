package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.constant.ArticleType;
import com.faishze.api.fasizheapi.dao.ArticleMapper;
import com.faishze.api.fasizheapi.dao.query.ArticleQuery;
import com.faishze.api.fasizheapi.pojo.do0.Article;
import com.faishze.api.fasizheapi.pojo.dto.ArticleDTO;
import com.faishze.api.fasizheapi.service.ArticleService;
import com.faishze.api.fasizheapi.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
    public ArticleDTO saveArticleDTO(ArticleDTO articleDTO) {
        Article article=new Article();
        article=dozerMapper.map(articleDTO,Article.class);
        //前期设定
        article.setAvailable(true);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        article.setViewNum((long) 0);
        article.setLikeNum(0);
        article.setCommentNum(0);
        article.setCollectionNum(0);
        int articleId=articleMapper.saveArticle(article);
        article.setId(articleId);
        articleDTO.setArticle(article);
        return articleDTO;
    }

    @Override
    public int deleteArticleDTO(Integer id) {
        return articleMapper.deleteArticle(id);
    }

    @Override
    public ArticleDTO getArticleDTO(Integer id) {
        ArticleDTO articleDTO=new ArticleDTO();
        Article article=articleMapper.getArticle(id);

        //DO转DTO
        articleDTO.setArticle(article);


        //调用userService设定authorName
        articleDTO.setAuthorName("allen");


        return articleDTO;
    }

    @Override
    public ArticleDTO updateArticleDTO(ArticleDTO articleDTO) {
        Article article=new Article();
        article=dozerMapper.map(articleDTO,Article.class);
        article.setUpdateTime(new Date());
        int articleId=articleMapper.updateArticle(article);
        return articleDTO;
    }

    @Override
    public Page<ArticleDTO> listArticleDTOs(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        Page<Article> articles=articleMapper.listArticles();
        Page<ArticleDTO> articleDTOS=new Page<>();
        for (Article article : articles) {
            articleDTOS.add(dozerMapper.map(article,ArticleDTO.class));
        }
        return articleDTOS;
    }

    @Override
    public Page<ArticleDTO> listArticleDTOsByQuery(Integer pageNum, Integer pageSize, ArticleQuery articleQuery) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Article> articles=articleMapper.listArticlesByQuery(articleQuery);
        Page<ArticleDTO> articleDTOS=new Page<>();
        for (Article article : articles) {
            articleDTOS.add(dozerMapper.map(article,ArticleDTO.class));
        }
        return articleDTOS;
    }

    public Page<ArticleDTO> listArticleDTOsByAvailableOderByLikeNumDESC(Integer pageNum, Integer pageSize){
        ArticleQuery articleQuery=new ArticleQuery(true,null,null,ArticleQuery.LIKE_NUM,ArticleQuery.DESC);
        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
    }

    //按收藏数降序，分页返回可用的文章
    public Page<ArticleDTO> listArticleDTOsByAvailableOderByCollentionNumDESC(Integer pageNum, Integer pageSize){
        ArticleQuery articleQuery=new ArticleQuery(true,null,null,ArticleQuery.COLLECTION_NUM,ArticleQuery.DESC);
        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
    }

    //按浏览数降序，分页返回可用的文章
    public Page<ArticleDTO> listArticleDTOsByAvailableOderByViewNumDESC(Integer pageNum, Integer pageSize){
        ArticleQuery articleQuery=new ArticleQuery(true,null,null,ArticleQuery.VIEW_NUM,ArticleQuery.DESC);
        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
    }

    //按评论数降序，分页返回可用的文章
    public Page<ArticleDTO> listArticleDTOsByAvailableOderByCommentNumDESC(Integer pageNum, Integer pageSize){
        ArticleQuery articleQuery=new ArticleQuery(true,null,null,ArticleQuery.COMMENT_NUM,ArticleQuery.DESC);
        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
    }

    //按更新时间降序，分页返回可用的文章
    public Page<ArticleDTO> listArticleDTOsByAvailableOderByUpateTimeDESC(Integer pageNum, Integer pageSize){
        ArticleQuery articleQuery=new ArticleQuery(true,null,null,ArticleQuery.UPDATE_TIME,ArticleQuery.DESC);
        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
    }

    //按类型，收藏数降序，分页返回可用的文章
    public Page<ArticleDTO> listArticleDTOsByAvailableAndTypeOderByCollectionDESC(Integer pageNum, Integer pageSize, ArticleType articleType){
        ArticleQuery articleQuery=new ArticleQuery(true,null,articleType,ArticleQuery.COLLECTION_NUM,ArticleQuery.DESC);
        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
    }

    //按类型，点赞数降序，分页返回可用的文章
    public Page<ArticleDTO> listArticleDTOsByAvailableAndTypeOderByLikeNumDESC(Integer pageNum, Integer pageSize, ArticleType articleType){
        ArticleQuery articleQuery=new ArticleQuery(true,null,articleType,ArticleQuery.LIKE_NUM,ArticleQuery.DESC);
        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
    }

    //按类型，浏览数降序，分页返回可用的文章
    public Page<ArticleDTO> listArticleDTOsByAvailableAndTypeOderByViewNumDESC(Integer pageNum, Integer pageSize, ArticleType articleType){
        ArticleQuery articleQuery=new ArticleQuery(true,null,articleType,ArticleQuery.VIEW_NUM,ArticleQuery.DESC);
        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
    }

    //按类型，评论数降序，分页返回可用的文章
    public Page<ArticleDTO> listArticleDTOsByAvailableAndTypeOderByCommentNumDESC(Integer pageNum, Integer pageSize, ArticleType articleType){
        ArticleQuery articleQuery=new ArticleQuery(true,null,articleType,ArticleQuery.COMMENT_NUM,ArticleQuery.DESC);
        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
    }

    //按类型，更新时间降序，分页返回可用的文章
    public Page<ArticleDTO> listArticleDTOsByAvailableAndTypeOderByUpateTimeDESC(Integer pageNum, Integer pageSize, ArticleType articleType){
        ArticleQuery articleQuery=new ArticleQuery(true,null,articleType,ArticleQuery.UPDATE_TIME,ArticleQuery.DESC);
        return listArticleDTOsByQuery(pageNum,pageSize,articleQuery);
    }


}
