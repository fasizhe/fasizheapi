package com.faishze.api.fasizheapi;

import com.faishze.api.fasizheapi.constant.ArticleType;
import com.faishze.api.fasizheapi.dao.ArticleMapper;
import com.faishze.api.fasizheapi.dao.query.ArticleQuery;
import com.faishze.api.fasizheapi.pojo.do0.Article;
import com.faishze.api.fasizheapi.service.FileService;
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
    FileService fileService;

    @Test
    public void contextLoads() {
        ArticleQuery articleQuery=new ArticleQuery(null,null,ArticleType.UGC,ArticleQuery.ID,ArticleQuery.DESC);
        for (Article article : articleMapper.listArticlesByQuery(articleQuery)) {
            System.out.println(article);
        }
    }

    @Test
    public void saveArticle(){
        ArticleType articleType=ArticleType.getArticleTypeById(0);
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
}
