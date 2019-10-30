package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.query.ArticleCommentQuery;
import com.faishze.api.fasizheapi.pojo.vo.ArticleCommentVO;
import com.faishze.api.fasizheapi.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/10/28
 */
@RestController("/article/comment")
public class ArticleCommentController {

    @Autowired
    ArticleCommentService articleCommentService;

    @GetMapping("/getlistByArticleId/{articleId}")
    public List<ArticleCommentVO> getlistByArticleId(@PathVariable("articleId") Integer articleId,
                                                     @RequestParam("pageNum")Integer pageNum,
                                                     @RequestParam("pageSize")Integer pageSize){
        //分页返回文章所有评论
        List<ArticleCommentVO> articleCommentVOList=new ArrayList<>();
        ArticleCommentVO articleCommentVO=new ArticleCommentVO();
        return null;
    }

    @GetMapping("/getlistByArticleIdAndQuery/{articleId}")
    public ArticleCommentVO getlistByArticleIdAndQuery(@PathVariable("articleId") Integer articleId,
                                                       @RequestParam("pageNum") Integer pageNum,
                                                       @RequestParam("pageSize") Integer pageSize,
                                                       @RequestParam("query") ArticleCommentQuery query){
        //按条件分页返回文章所有评论
        return null;
    }
}
