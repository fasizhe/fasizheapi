package com.faishze.api.fasizheapi.pojo.ao;

import com.faishze.api.fasizheapi.constant.ArticleType;

import javax.validation.constraints.NotNull;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/10/30
 */
public class ArticleOldAO {

    @NotNull(message = "文章Id不能为空")
    private Integer id;

    @NotNull(message = "标题不能为空")
    private String title;

    @NotNull(message = "类型不能为空")
    private ArticleType type;

    @NotNull(message = "内容不能为空")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleOldAO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", content='" + content + '\'' +
                '}';
    }
}
