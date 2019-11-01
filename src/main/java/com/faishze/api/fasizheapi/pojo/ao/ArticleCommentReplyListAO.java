package com.faishze.api.fasizheapi.pojo.ao;

import com.faishze.api.fasizheapi.query.ArticleCommentReplyQuery;

import javax.validation.constraints.NotNull;

/**
 * @author 杜科
 * @description 查询文章评论回复的表单
 * @contact 15521177704
 * @since 2019/11/1
 */
public class ArticleCommentReplyListAO {

    @NotNull(message = "当前页数不能为空")
    private Integer pageNum;

    @NotNull(message = "页大小不能为空")
    private Integer pageSize;

    private ArticleCommentReplyQuery articleCommentReplyQuery;

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

    public ArticleCommentReplyQuery getArticleCommentReplyQuery() {
        return articleCommentReplyQuery;
    }

    public void setArticleCommentReplyQuery(ArticleCommentReplyQuery articleCommentReplyQuery) {
        this.articleCommentReplyQuery = articleCommentReplyQuery;
    }

    @Override
    public String toString() {
        return "ArticleCommentReplyListAO{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", articleCommentReplyQuery=" + articleCommentReplyQuery +
                '}';
    }
}
