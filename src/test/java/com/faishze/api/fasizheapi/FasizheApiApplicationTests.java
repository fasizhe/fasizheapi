package com.faishze.api.fasizheapi;

import com.faishze.api.fasizheapi.dao.ArticleMapper;
import com.faishze.api.fasizheapi.pojo.do0.Article;
import com.faishze.api.fasizheapi.util.JedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FasizheApiApplication.class)
public class FasizheApiApplicationTests {

    @Autowired
    ArticleMapper articleMapper;

    @Value("${redis.articlesScoreInId.key}")
    String articlesScoreInIdKey;

    @Value("${redis.articlesScoreInViewNum.key}")
    String articlesScoreInViewNumKey;

    @Test
    public void testJedis() throws IllegalAccessException, InvocationTargetException {
        Article article=new Article();
//        article.setId(10);
//        article.setUserId(1);
//        article.setAvailable(true);
//        article.setContent("sdf");
//        article.setTitle("sdfasd");
//        article.setCollectionNum(0);
//        article.setCommentNum(0);
//        article.setLikeNum(0);
//        article.setViewNum((long) 0);
//        article.setCreateTime(new Date());
//        article.setUpdateTime(new Date());
//        article.setType(ArticleType.UGC);
//        JedisUtil.hsetObject("article_"+article.getId(),article);
       // article= (Article) JedisUtil.hgetObject("article_10",article);
        System.out.println(article);
    }

    @Test
    public void testZset(){
        List<Article> list=articleMapper.listArticles();
        for (Article article : list) {
            JedisUtil.zsetMember(articlesScoreInIdKey,article.getId().doubleValue(),article.getId());
            JedisUtil.zsetMember(articlesScoreInViewNumKey,article.getViewNum().doubleValue(),article.getId());
        }
        List<Integer> articleIds=JedisUtil.zgetMember(articlesScoreInViewNumKey,1,2,Integer.TYPE);
        for (Integer articleId : articleIds) {
            System.out.println(articleMapper.getArticle(articleId));
        }
    }


}
