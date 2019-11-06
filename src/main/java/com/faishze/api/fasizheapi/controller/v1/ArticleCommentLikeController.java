package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.pojo.ao.ArticleCommentLikeAO;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentLikeDTO;
import com.faishze.api.fasizheapi.pojo.vo.ArticleCommentLikeVO;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleCommentLikeService;
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
@RequestMapping("/article/comment/like")
public class ArticleCommentLikeController {

    @Autowired
    ArticleCommentLikeService articleCommentLikeService;

    @Autowired
    Mapper dozerMapper;

    @PostMapping("/save")
    public ArticleCommentLikeVO save(@RequestBody @Validated ArticleCommentLikeAO articleCommentLikeAO) {
        ArticleCommentLikeDTO articleCommentLikeDTO = dozerMapper.map(articleCommentLikeAO,
                ArticleCommentLikeDTO.class);
        articleCommentLikeDTO =
                (ArticleCommentLikeDTO) articleCommentLikeService.saveArticleCommentLikeDTO(articleCommentLikeDTO).getData();
        ArticleCommentLikeVO articleCommentLikeVO = dozerMapper.map(articleCommentLikeDTO, ArticleCommentLikeVO.class);
        return articleCommentLikeVO;
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("articleCommentLikeId") Long id) {
        return articleCommentLikeService.deleteArticleCommentLikeDTO(id);
    }

    @PostMapping("/getListByQuery")
    public List<ArticleCommentLikeVO> getListByQuery(@RequestParam("articleCommentLikeAOs") List<ArticleCommentLikeAO> articleCommentLikeAOS) {
        List<ArticleCommentLikeVO> articleCommentLikeVOS = new ArrayList<>();
        ArticleCommentLikeVO articleCommentLikeVO;
        ArticleCommentLikeDTO articleCommentLikeDTO;
        Result result;
        for (ArticleCommentLikeAO articleCommentLikeAO : articleCommentLikeAOS) {
            //articleCommentLikeDTO=dozerMapper.map(articleCommentLikeAO,ArticleCommentLikeDTO.class);
            result=articleCommentLikeService.getArticleCommentLikeDTOsByUserIdAndCommentId(articleCommentLikeAO.getUserId(),
                    articleCommentLikeAO.getCommentId());
            if (result.isSuccess()){
                articleCommentLikeDTO= (ArticleCommentLikeDTO) result.getData();
            }else{
                articleCommentLikeDTO=new ArticleCommentLikeDTO();
                articleCommentLikeDTO.setId((long) -1);
            }
            articleCommentLikeVO=dozerMapper.map(articleCommentLikeDTO,ArticleCommentLikeVO.class);
            articleCommentLikeVOS.add(articleCommentLikeVO);
        }
        return articleCommentLikeVOS;
    }

}
