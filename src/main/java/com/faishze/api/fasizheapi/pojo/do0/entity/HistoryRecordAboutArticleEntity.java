package com.faishze.api.fasizheapi.pojo.do0.entity;

import com.faishze.api.fasizheapi.pojo.do0.Article;

import java.util.Date;

/**
 * @author 杜科
 * @description 关于文章的历史记录
 * @contact 15521177704
 * @since 2019/10/27
 */
public class HistoryRecordAboutArticleEntity {

    private Article article;

    //首次浏览
    private Date firstTime;

    //最后一次浏览
    private Date lastTime;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public String toString() {
        return "HistoryRecordAboutArticleEntity{" +
                "article=" + article +
                ", firstTime=" + firstTime +
                ", lastTime=" + lastTime +
                '}';
    }
}
