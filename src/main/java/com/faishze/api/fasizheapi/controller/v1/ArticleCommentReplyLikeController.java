package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.pojo.ao.ArticleCommentReplyLikeAO;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentReplyLikeDTO;
import com.faishze.api.fasizheapi.pojo.vo.ArticleCommentReplyLikeVO;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleCommentReplyLikeService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杜科
 * @description
 * @contact 15521177704
 * @since 2019/11/3
 */
@RestController
@RequestMapping("/article/comment/reply/like")
public class ArticleCommentReplyLikeController {

    @Autowired
    ArticleCommentReplyLikeService articleCommentReplyLikeService;

    @Autowired
    Mapper dozerMapper;

    @PostMapping("/save")
    public ArticleCommentReplyLikeVO save(@RequestBody @Validated ArticleCommentReplyLikeAO articleCommentReplyLikeAO) {
        ArticleCommentReplyLikeDTO articleCommentReplyLikeDTO = dozerMapper.map(articleCommentReplyLikeAO,
                ArticleCommentReplyLikeDTO.class);
        articleCommentReplyLikeDTO =
                (ArticleCommentReplyLikeDTO) articleCommentReplyLikeService.saveArticleCommentReplyLikeDTO(articleCommentReplyLikeDTO).getData();
        ArticleCommentReplyLikeVO articleCommentReplyLikeVO = dozerMapper.map(articleCommentReplyLikeDTO, ArticleCommentReplyLikeVO.class);
        return articleCommentReplyLikeVO;
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("articleCommentReplyLikeId") Long id) {
        return articleCommentReplyLikeService.deleteArticleCommentReplyLikeDTO(id);
    }

    @GetMapping("/getListByQuery")
    public List<ArticleCommentReplyLikeVO> getListByQuery(@RequestBody List<ArticleCommentReplyLikeAO> articleCommentReplyLikeAOS) {
        List<ArticleCommentReplyLikeVO> articleCommentReplyLikeVOS = new ArrayList<>();
        ArticleCommentReplyLikeVO articleCommentReplyLikeVO;
        ArticleCommentReplyLikeDTO articleCommentReplyLikeDTO;
        Result result;
        for (ArticleCommentReplyLikeAO articleCommentReplyLikeAO : articleCommentReplyLikeAOS) {
            //articleCommentReplyLikeDTO=dozerMapper.map(articleCommentReplyLikeAO,ArticleCommentReplyLikeDTO.class);
            result=articleCommentReplyLikeService.getArticleCommentReplyLikeDTOsByUserIdAndReplyId(articleCommentReplyLikeAO.getUserId(),
                    articleCommentReplyLikeAO.getReplyId());
            if (result.isSuccess()){
                articleCommentReplyLikeDTO= (ArticleCommentReplyLikeDTO) result.getData();
            }else{
                articleCommentReplyLikeDTO=new ArticleCommentReplyLikeDTO();
                articleCommentReplyLikeDTO.setId((long) -1);
            }
            articleCommentReplyLikeVO=dozerMapper.map(articleCommentReplyLikeDTO,ArticleCommentReplyLikeVO.class);
            articleCommentReplyLikeVOS.add(articleCommentReplyLikeVO);
        }
        return articleCommentReplyLikeVOS;
    }
}
