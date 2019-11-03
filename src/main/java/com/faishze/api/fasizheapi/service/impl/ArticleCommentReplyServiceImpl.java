package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.ArticleCommentReplyMapper;
import com.faishze.api.fasizheapi.pojo.do0.ArticleCommentReply;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentDTO;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentReplyDTO;
import com.faishze.api.fasizheapi.query.ArticleCommentReplyQuery;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleCommentReplyService;
import com.faishze.api.fasizheapi.service.ArticleCommentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author masonluo
 * @date 2019/10/27 11:28 PM
 */
@Service
public class ArticleCommentReplyServiceImpl implements ArticleCommentReplyService {

    @Autowired
    ArticleCommentReplyMapper articleCommentReplyMapper;

    @Autowired
    ArticleCommentService articleCommentService;

    @Autowired
    Mapper dozerMapper;

    @Override
    public Result saveArticleCommentReplyDTO(ArticleCommentReplyDTO record) {
        //做入库前的处理
        ArticleCommentReply articleCommentReply = dozerMapper.map(record, ArticleCommentReply.class);
        articleCommentReplyMapper.saveArticleCommentReply(articleCommentReply);
        record.setId(articleCommentReply.getId());
        //评论回复数+1
        ArticleCommentDTO articleCommentDTO =
                (ArticleCommentDTO) articleCommentService.getArticleCommentDTO(record.getCommentId()).getData();
        articleCommentDTO.setReplyNum(articleCommentDTO.getReplyNum()+1);
        articleCommentService.updateArticleCommentDTO(articleCommentDTO);

        return Result.success(record);
    }

    @Override
    public Result deleteArticleCommentReplyDTO(Long id) {
        articleCommentReplyMapper.deleteArticleCommentReply(id);
        return Result.success();
    }

    @Override
    public Result getArticleCommentReplyDTO(Long id) {
        ArticleCommentReply articleCommentReply = articleCommentReplyMapper.getArticleCommentReply(id);
        ArticleCommentReplyDTO articleCommentReplyDTO = dozerMapper.map(articleCommentReply,
                ArticleCommentReplyDTO.class);
        return Result.success(articleCommentReplyDTO);
    }

    @Override
    public Result updateArticleCommentReplyDTO(ArticleCommentReplyDTO record) {
        //做入库前的处理


        ArticleCommentReply articleCommentReply = dozerMapper.map(record, ArticleCommentReply.class);
        articleCommentReplyMapper.updateArticleCommentReply(articleCommentReply);
        return Result.success(record);

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
}
