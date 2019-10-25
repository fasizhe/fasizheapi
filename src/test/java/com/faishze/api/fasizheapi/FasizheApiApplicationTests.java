package com.faishze.api.fasizheapi;

import com.faishze.api.fasizheapi.dao.ArticleMapper;
import com.faishze.api.fasizheapi.dao.query.ArticleQuery;
import com.faishze.api.fasizheapi.pojo.do0.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FasizheApiApplicationTests {

    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void contextLoads() {
        Article article=new Article();
        article.setUserId(1);
        articleMapper.saveArticle(article);
        ArticleQuery articleQuery=new ArticleQuery(null,null,null,null,null);
        System.out.println(articleMapper.listArticlesByQuery(articleQuery));
        System.out.println(articleMapper.listArticles());
    }

}
