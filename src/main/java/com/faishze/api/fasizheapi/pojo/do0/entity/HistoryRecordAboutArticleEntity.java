package com.faishze.api.fasizheapi.pojo.do0.entity;

import com.faishze.api.fasizheapi.constant.HistoryRecordType;
import com.faishze.api.fasizheapi.pojo.do0.Article;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 杜科
 * @description 关于文章的历史记录
 * @contact 15521177704
 * @since 2019/10/27
 */
public class HistoryRecordAboutArticleEntity implements Serializable {

    private static final long serialVersionUID = 1792919453873866506L;
    private Long id;

    private Integer userId;

    private HistoryRecordType type;

    private Article article;

    //首次浏览
    private Date createTime;

    //最近一次浏览
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public HistoryRecordType getType() {
        return type;
    }

    public void setType(HistoryRecordType type) {
        this.type = type;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "HistoryRecordAboutArticleEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", type=" + type +
                ", article=" + article +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
