package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.ArticleCommentReplyMapper;
import com.faishze.api.fasizheapi.pojo.do0.ArticleCommentReply;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentReplyDTO;
import com.faishze.api.fasizheapi.query.ArticleCommentReplyQuery;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleCommentReplyService;
import com.faishze.api.fasizheapi.service.ArticleCommentService;
import com.faishze.api.fasizheapi.util.JedisUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author masonluo
 * @date 2019/10/27 11:28 PM
 */
@Service
@Transactional
public class ArticleCommentReplyServiceImpl implements ArticleCommentReplyService {

    @Autowired
    ArticleCommentReplyMapper articleCommentReplyMapper;

    @Autowired
    ArticleCommentService articleCommentService;

    @Autowired
    Mapper dozerMapper;

    @Value("${redis.reply.key}")
    String articleCommentReplyKey;

    @Override
    public Result saveArticleCommentReplyDTO(ArticleCommentReplyDTO record) {
        //做入库前的处理
        ArticleCommentReply articleCommentReply = dozerMapper.map(record, ArticleCommentReply.class);
        articleCommentReplyMapper.saveArticleCommentReply(articleCommentReply);
        record.setId(articleCommentReply.getId());
        //评论回复数+1
        articleCommentService.riseRplyNum(record.getCommentId());

        return Result.success(record);
    }

    @Override
    public Result deleteArticleCommentReplyDTO(Long id) {
        articleCommentReplyMapper.deleteArticleCommentReply(id);
        return Result.success();
    }

    @Override
    public Result getArticleCommentReplyDTO(Long id) {
        ArticleCommentReplyDTO articleCommentReplyDTO;
        ArticleCommentReply articleCommentReply = new ArticleCommentReply();
        articleCommentReplyKey = articleCommentReplyKey + id;
        //检查缓存
        if (JedisUtil.isExist(articleCommentReplyKey)) {
            articleCommentReply = (ArticleCommentReply) JedisUtil.getObject(articleCommentReplyKey,
                    articleCommentReply);
            System.out.println("读取回复缓存");
        } else {
            articleCommentReply = articleCommentReplyMapper.getArticleCommentReply(id);
            JedisUtil.setObject(articleCommentReplyKey, articleCommentReply);
            System.out.println("读取数据库，并写到回复缓存");
        }
        //DO转DTO
        articleCommentReplyDTO = dozerMapper.map(articleCommentReply, ArticleCommentReplyDTO.class);
        Result result = Result.success(articleCommentReplyDTO);
        return result;
    }

    @Override
    public Result updateArticleCommentReplyDTO(ArticleCommentReplyDTO record) {
        Long replyId = record.getId();
        ArticleCommentReply articleCommentReply = dozerMapper.map(record, ArticleCommentReply.class);
        articleCommentReplyKey = articleCommentReplyKey + replyId;
        JedisUtil.setObject(articleCommentReplyKey, articleCommentReply);
        System.out.println("更新回复缓存");
        //写回数据库
        articleCommentReplyMapper.updateArticleCommentReply(articleCommentReply);
        Result result = Result.success(record);
        return result;

    }

    @Override
    public Result listArticleCommentReplyDTOs(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ArticleCommentReply> articleCommentReplys = articleCommentReplyMapper.listArticleCommentReplys();
        Page<ArticleCommentReplyDTO> articleCommentReplyDTOS = new Page<>();
        for (ArticleCommentReply articleCommentReply : articleCommentReplys) {
            articleCommentReplyDTOS.add(dozerMapper.map(articleCommentReply, ArticleCommentReplyDTO.class));
        }
        Result result = Result.success(articleCommentReplyDTOS);
        return result;
    }

    @Override
    public Result listArticleCommentReplyDTOsByQuery(Integer pageNum, Integer pageSize,
                                                     ArticleCommentReplyQuery articleCommentReplyQuery) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ArticleCommentReply> articleCommentReplys =
                articleCommentReplyMapper.listArticleCommentReplysByQuery(articleCommentReplyQuery);
        Page<ArticleCommentReplyDTO> articleCommentReplyDTOS = new Page<>();
        for (ArticleCommentReply articleCommentReply : articleCommentReplys) {
            articleCommentReplyDTOS.add(dozerMapper.map(articleCommentReply, ArticleCommentReplyDTO.class));
        }
        Result result = Result.success(articleCommentReplyDTOS);
        return result;
    }

    @Override
    public Result clearArticleCommentReplyDTO(Long id) {
        ArticleCommentReply articleCommentReply = articleCommentReplyMapper.getArticleCommentReply(id);
        articleCommentReply.setContent("该回复已被删除");
        articleCommentReplyMapper.updateArticleCommentReply(articleCommentReply);
        return Result.success(articleCommentReply);
    }

    @Override
    public Result riseLikeNum(Long id) {
        ArticleCommentReplyDTO articleCommentReplyDTO =
                (ArticleCommentReplyDTO) getArticleCommentReplyDTO(id).getData();
        articleCommentReplyDTO.setLikeNum(articleCommentReplyDTO.getLikeNum()+1);
        updateArticleCommentReplyDTO(articleCommentReplyDTO);
        return Result.success();
    }

    @Override
    public Result reduceLikeNum(Long id) {
        ArticleCommentReplyDTO articleCommentReplyDTO =
                (ArticleCommentReplyDTO) getArticleCommentReplyDTO(id).getData();
        articleCommentReplyDTO.setLikeNum(articleCommentReplyDTO.getLikeNum()-1);
        updateArticleCommentReplyDTO(articleCommentReplyDTO);
        return Result.success();
    }
}
