package com.faishze.api.fasizheapi.pojo.ao;

import com.faishze.api.fasizheapi.query.ArticleQuery;

import javax.validation.constraints.NotNull;

/**
 * @author 杜科
 * @description 用户获取相应文章列表要填写的表单
 * @contact 15521177704
 * @since 2019/10/30
 */
public class ArticleListAO {

    @NotNull(message = "当前页数不能为空")
    private Integer pageNum;

    @NotNull(message = "页大小不能为空")
    private Integer pageSize;

    //查询对象
    private ArticleQuery articleQuery;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public ArticleQuery getArticleQuery() {
        return articleQuery;
    }

    public void setArticleQuery(ArticleQuery articleQuery) {
        this.articleQuery = articleQuery;
    }

    @Override
    public String toString() {
        return "ArticleListAO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", articleQuery=" + articleQuery +
                '}';
    }
}
