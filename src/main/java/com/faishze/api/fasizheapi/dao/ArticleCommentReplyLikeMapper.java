package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ArticleCommentReplyLike;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ArticleCommentReplyLikeMapper {
    int deleteArticleCommentReplyLike(Long id);

    int saveArticleCommentReplyLike(ArticleCommentReplyLike record);

    ArticleCommentReplyLike getArticleCommentReplyLike(Long id);

    Page<ArticleCommentReplyLike> listArticleCommentReplyLikes();

    int updateArticleCommentReplyLike(ArticleCommentReplyLike record);

    ArticleCommentReplyLike getArticleCommentReplyLikeByUserIdAndReplyId(Integer userId, Long replyId);

    Page<ArticleCommentReplyLike> listArticleCommentReplyLikesByReplyIdAndDeadTime(Long replyId, Date deadTime);
}