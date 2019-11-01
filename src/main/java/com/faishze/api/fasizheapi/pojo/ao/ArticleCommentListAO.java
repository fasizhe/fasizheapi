package com.faishze.api.fasizheapi.pojo.ao;

import com.faishze.api.fasizheapi.query.ArticleCommentQuery;

import javax.validation.constraints.NotNull;

/**
 * @author 杜科
 * @description 查询文章评论的表单
 * @contact 15521177704
 * @since 2019/11/1
 */
public class ArticleCommentListAO {

    @NotNull(message = "当前页数不能为空")
    private Integer pageNum;

    @NotNull(message = "页大小不能为空")
    private Integer pageSize;

    private ArticleCommentQuery articleCommentQuery;

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

    public ArticleCommentQuery getArticleCommentQuery() {
        return articleCommentQuery;
    }

    public void setArticleCommentQuery(ArticleCommentQuery articleCommentQuery) {
        this.articleCommentQuery = articleCommentQuery;
    }

    @Override
    public String toString() {
        return "ArticleCommentListAO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", articleCommentQuery=" + articleCommentQuery +
                '}';
    }
}
