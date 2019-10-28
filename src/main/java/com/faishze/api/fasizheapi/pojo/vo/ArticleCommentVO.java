package com.faishze.api.fasizheapi.pojo.vo;

import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentDTO;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/10/28
 */
public class ArticleCommentVO {

    //评论人的评论
    private ArticleCommentDTO articleCommentDTO;

    public ArticleCommentDTO getArticleCommentDTO() {
        return articleCommentDTO;
    }

    public void setArticleCommentDTO(ArticleCommentDTO articleCommentDTO) {
        this.articleCommentDTO = articleCommentDTO;
    }

    @Override
    public String toString() {
        return "ArticleCommentVO{" +
                "articleCommentDTO=" + articleCommentDTO +
                '}';
    }
}
