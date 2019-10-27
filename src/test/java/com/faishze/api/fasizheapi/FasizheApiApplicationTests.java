package com.faishze.api.fasizheapi;

import com.faishze.api.fasizheapi.constant.ArticleType;
import com.faishze.api.fasizheapi.dao.ArticleCollectionMapper;
import com.faishze.api.fasizheapi.dao.ArticleMapper;
import com.faishze.api.fasizheapi.dao.HistoryRecordMapper;
import com.faishze.api.fasizheapi.dao.query.ArticleQuery;
import com.faishze.api.fasizheapi.pojo.do0.Article;
import com.faishze.api.fasizheapi.pojo.do0.entity.AritcleCollectionEntity;
import com.faishze.api.fasizheapi.pojo.do0.entity.HistoryRecordAboutArticleEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FasizheApiApplicationTests {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleCollectionMapper articleCollectionMapper;

    @Autowired
    HistoryRecordMapper historyRecordMapper;

    @Test
    public void getArticles() {
        ArticleQuery articleQuery=new ArticleQuery(null,null,ArticleType.UGC,ArticleQuery.CREATE_TIME,ArticleQuery.DESC);
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

}
