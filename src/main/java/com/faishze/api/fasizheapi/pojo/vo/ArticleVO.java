package com.faishze.api.fasizheapi.pojo.vo;

import com.faishze.api.fasizheapi.pojo.dto.ArticleDTO;

/**
 * @author 杜科
 * @description 文章视图
 * @contact 15521177704
 * @since 2019/10/28
 */
public class ArticleVO {

    //文章
    private ArticleDTO articleDTO;

    public ArticleDTO getArticleDTO() {
        return articleDTO;
    }

    public void setArticleDTO(ArticleDTO articleDTO) {
        this.articleDTO = articleDTO;
    }

    @Override
    public String toString() {
        return "ArticleVO{" +
                "articleDTO=" + articleDTO +
                '}';
    }
}
