package com.faishze.api.fasizheapi;

import com.faishze.api.fasizheapi.constant.ArticleType;
import com.faishze.api.fasizheapi.dao.ArticleCollectionMapper;
import com.faishze.api.fasizheapi.dao.ArticleMapper;
import com.faishze.api.fasizheapi.dao.HistoryRecordMapper;
import com.faishze.api.fasizheapi.pojo.do0.Article;
<<<<<<< HEAD
import com.faishze.api.fasizheapi.pojo.do0.entity.AritcleCollectionEntity;
import com.faishze.api.fasizheapi.pojo.do0.entity.HistoryRecordAboutArticleEntity;
import com.faishze.api.fasizheapi.query.ArticleQuery;
import com.faishze.api.fasizheapi.query.Query;
=======
import com.faishze.api.fasizheapi.service.FileService;
>>>>>>> masonluo
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FasizheApiApplication.class)
public class FasizheApiApplicationTests {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
<<<<<<< HEAD
    ArticleCollectionMapper articleCollectionMapper;

    @Autowired
    HistoryRecordMapper historyRecordMapper;
=======
    FileService fileService;
>>>>>>> masonluo

    @Test
    public void getArticles() {
        ArticleQuery articleQuery=new ArticleQuery(null,null,null,ArticleType.UGC, Query.CREATE_TIME,Query.DESC);
        for (Article article : articleMapper.listArticlesByQuery(articleQuery)) {
            System.out.println(article);
        }
    }

    @Test
    public void saveArticle(){
        Article article=new Article();
        article.setUserId(1);
        article.setAvailable(true);
        article.setContent("sdf");
        article.setTitle("sdfasd");
        article.setCollectionNum(0);
        article.setCommentNum(0);
        article.setLikeNum(0);
        article.setViewNum((long) 0);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        article.setType(ArticleType.UGC);
        articleMapper.saveArticle(article);
    }
<<<<<<< HEAD

    @Test
    public void listArticlesByCollectorId(){
        Integer userId=1;
        for (AritcleCollectionEntity aritcleCollectionEntity : articleCollectionMapper.listArticleCollectionEntitysByCollectorId(userId)) {
            System.out.println(aritcleCollectionEntity);
        }
    }

    @Test
    public void listHistoryRecordAboutArticleEntity(){
        for (HistoryRecordAboutArticleEntity historyRecordAboutArticleEntity :
                historyRecordMapper.listHistoryRecordAboutArticleEntitysByUserId(2)) {
            System.out.println(historyRecordAboutArticleEntity);
        }
    }

=======
>>>>>>> masonluo
}
