package com.faishze.api.fasizheapi.pojo.ao;

import com.faishze.api.fasizheapi.constant.ArticleType;

import javax.validation.constraints.NotNull;

/**
 * @author 杜科 前端传入的文章表单
 * @description
 * @contact 15521177704
 * @since 2019/10/28
 */
public class ArticleNewAO {

    @NotNull(message = "用户Id不能为空")
    private Integer userId;

    @NotNull(message = "用户昵称不能为空")
    private String userNickName;

    @NotNull(message = "标题不能为空")
    private String title;

    @NotNull(message = "类型不能为空")
    private ArticleType type;

    @NotNull(message = "内容不能为空")
    private String content;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
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
        return "ArticleAO{" +
                "userId=" + userId +
                ", userNickName='" + userNickName + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", content='" + content + '\'' +
                '}';
    }
}
