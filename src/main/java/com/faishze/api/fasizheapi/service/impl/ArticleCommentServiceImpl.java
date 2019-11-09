package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.ArticleCommentMapper;
import com.faishze.api.fasizheapi.pojo.do0.ArticleComment;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentDTO;
import com.faishze.api.fasizheapi.query.ArticleCommentQuery;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleCommentService;
import com.faishze.api.fasizheapi.service.ArticleService;
import com.faishze.api.fasizheapi.util.JedisUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/10/27
 */
@Service
@Transactional
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Autowired
    ArticleCommentMapper articleCommentMapper;

    @Autowired
    ArticleService articleService;

    @Autowired
    Mapper dozerMapper;

    @Value("${redis.comment.key}")
    String articleCommentKey;

    @Override
    public Result saveArticleCommentDTO(ArticleCommentDTO record) {
        //做入库前的处理
        record.setReplyNum(0);
        record.setLikeNum(0);
        ArticleComment articleComment = dozerMapper.map(record, ArticleComment.class);
        articleCommentMapper.saveArticleComment(articleComment);
        record.setId(articleComment.getId());
        Result result = Result.success(record);
        //文章评论数+1
        Integer articleId = record.getArticleId();
        articleService.riseCommentNum(articleId);

        return result;
    }

    @Override
    public Result deleteArticleCommentDTO(Long id) {
        articleCommentMapper.deleteArticleComment(id);
        return Result.success();
    }

    @Override
    public Result getArticleCommentDTO(Long id) {
        ArticleCommentDTO articleCommentDTO;
        ArticleComment articleComment = new ArticleComment();
        articleCommentKey = articleCommentKey + id;
        //检查缓存
        if (JedisUtil.isExist(articleCommentKey)) {
            articleComment = (ArticleComment) JedisUtil.getObject(articleCommentKey, articleComment);
            System.out.println("读取评论缓存");
        } else {
            articleComment = articleCommentMapper.getArticleComment(id);
            JedisUtil.setObject(articleCommentKey, articleComment);
            System.out.println("读取数据库，并写到评论缓存");
        }
        //DO转DTO
        articleCommentDTO = dozerMapper.map(articleComment, ArticleCommentDTO.class);
        Result result = Result.success(articleCommentDTO);
        return result;
    }

    @Override
    public Result updateArticleCommentDTO(ArticleCommentDTO record) {
        Long commentId = record.getId();
        ArticleComment articleComment = dozerMapper.map(record,ArticleComment.class);
        articleCommentKey = articleCommentKey + commentId;
        JedisUtil.setObject(articleCommentKey, articleComment);
        System.out.println("更新评论缓存");
        //写回数据库
        articleCommentMapper.updateArticleComment(articleComment);
        Result result = Result.success(record);
        return result;
    }

    @Override
    public Result listArticleCommentDTOs(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ArticleComment> articleComments = articleCommentMapper.listArticleComments();
        Page<ArticleCommentDTO> articleCommentDTOS = new Page<>();
        for (ArticleComment articleComment : articleComments) {
            articleCommentDTOS.add(dozerMapper.map(articleComment, ArticleCommentDTO.class));
        }
        Result result = Result.success(articleCommentDTOS);
        return result;
    }

    @Override
    public Result listArticleCommentDTOsByQuery(Integer pageNum, Integer pageSize,
                                                ArticleCommentQuery articleCommentQuery) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ArticleComment> articleComments = articleCommentMapper.listArticleCommentsByQuery(articleCommentQuery);
        Page<ArticleCommentDTO> articleCommentDTOS = new Page<>();
        for (ArticleComment articleComment : articleComments) {
            articleCommentDTOS.add(dozerMapper.map(articleComment, ArticleCommentDTO.class));
        }
        Result result = Result.success(articleCommentDTOS);
        return result;
    }

    @Override
    public Result clearArticleCommentDTO(Long id) {
        ArticleComment articleComment = articleCommentMapper.getArticleComment(id);
        articleComment.setContent("该评论已被删除");
        articleCommentMapper.updateArticleComment(articleComment);
        return Result.success(articleComment);
    }

    @Override
    public Result riseLikeNum(Long commentId) {
        ArticleCommentDTO articleCommentDTO = (ArticleCommentDTO) getArticleCommentDTO(commentId).getData();
        articleCommentDTO.setLikeNum(articleCommentDTO.getLikeNum()+1);
        updateArticleCommentDTO(articleCommentDTO);
        return Result.success();
    }

    @Override
    public Result reduceLikeNum(Long commentId){
        ArticleCommentDTO articleCommentDTO = (ArticleCommentDTO) getArticleCommentDTO(commentId).getData();
        articleCommentDTO.setLikeNum(articleCommentDTO.getLikeNum()-1);
        updateArticleCommentDTO(articleCommentDTO);
        return Result.success();
    }

    @Override
    public Result riseRplyNum(Long commentId){
        ArticleCommentDTO articleCommentDTO = (ArticleCommentDTO) getArticleCommentDTO(commentId).getData();
        articleCommentDTO.setReplyNum(articleCommentDTO.getReplyNum()+1);
        updateArticleCommentDTO(articleCommentDTO);
        return Result.success();
    }

}
