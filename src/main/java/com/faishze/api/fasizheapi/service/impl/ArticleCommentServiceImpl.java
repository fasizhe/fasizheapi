package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.ArticleCommentMapper;
import com.faishze.api.fasizheapi.pojo.do0.ArticleComment;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentDTO;
import com.faishze.api.fasizheapi.query.ArticleCommentQuery;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleCommentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    Mapper dozerMapper;

    @Override
    public Result saveArticleCommentDTO(ArticleCommentDTO record) {
        //做入库前的处理

        ArticleComment articleComment=dozerMapper.map(record,ArticleComment.class);
        articleCommentMapper.saveArticleComment(articleComment);
        record.setId(articleComment.getId());
        Result result=Result.success(record);
        return result;
    }

    @Override
    public Result deleteArticleCommentDTO(Long id) {
        articleCommentMapper.deleteArticleComment(id);
        return Result.success();
    }

    @Override
    public Result getArticleCommentDTO(Long id) {
        ArticleComment articleComment=articleCommentMapper.getArticleComment(id);
        ArticleCommentDTO articleCommentDTO=dozerMapper.map(articleComment,ArticleCommentDTO.class);
        return Result.success(articleCommentDTO);
    }

    @Override
    public Result updateArticleCommentDTO(ArticleCommentDTO record) {
        //做入库前的处理

        ArticleComment articleComment=dozerMapper.map(record,ArticleComment.class);
        articleCommentMapper.updateArticleComment(articleComment);
        Result result=Result.success(record);
        return result;
    }

    @Override
    public Result listArticleCommentDTOs(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ArticleComment> articleComments=articleCommentMapper.listArticleComments();
        Page<ArticleCommentDTO> articleCommentDTOS=new Page<>();
        for (ArticleComment articleComment : articleComments) {
            articleCommentDTOS.add(dozerMapper.map(articleComment,ArticleCommentDTO.class));
        }
        Result result=Result.success(articleCommentDTOS);
        return result ;
    }

    @Override
    public Result listArticleCommentDTOsByQuery(Integer pageNum, Integer pageSize,
                                                                 ArticleCommentQuery articleCommentQuery) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ArticleComment> articleComments=articleCommentMapper.listArticleCommentsByQuery(articleCommentQuery);
        Page<ArticleCommentDTO> articleCommentDTOS=new Page<>();
        for (ArticleComment articleComment : articleComments) {
            articleCommentDTOS.add(dozerMapper.map(articleComment,ArticleCommentDTO.class));
        }
        Result result=Result.success(articleCommentDTOS);
        return result ;
    }

    @Override
    public Result clearArticleCommentDTO(Long id){
        ArticleComment articleComment=articleCommentMapper.getArticleComment(id);
        articleComment.setContent("该评论已被删除");
        articleCommentMapper.updateArticleComment(articleComment);
        return Result.success(articleComment);
    }
}
