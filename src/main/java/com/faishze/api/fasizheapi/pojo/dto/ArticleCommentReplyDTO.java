package com.faishze.api.fasizheapi.pojo.dto;

import com.faishze.api.fasizheapi.pojo.do0.ArticleCommentReply;

/**
 * @author 杜科
 * @description 封装回复发起者名字，接受者名字
 * @contact 15521177704
 * @since 2019/10/28
 */
public class ArticleCommentReplyDTO {

    //发起者名字
    private String senderName;

    //接受者名字
    private String receiverName;

    private ArticleCommentReply articleCommentReply;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public ArticleCommentReply getArticleCommentReply() {
        return articleCommentReply;
    }

    public void setArticleCommentReply(ArticleCommentReply articleCommentReply) {
        this.articleCommentReply = articleCommentReply;
    }

    @Override
    public String toString() {
        return "ArticleCommentReplyDTO{" +
                "senderName='" + senderName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", articleCommentReply=" + articleCommentReply +
                '}';
    }
}
