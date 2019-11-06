package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.pojo.ao.ArticleCommentListAO;
import com.faishze.api.fasizheapi.pojo.ao.ArticleCommentAO;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentDTO;
import com.faishze.api.fasizheapi.pojo.vo.ArticleCommentVO;
import com.faishze.api.fasizheapi.query.ArticleCommentQuery;
import com.faishze.api.fasizheapi.service.ArticleCommentService;
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
 * @since 2019/10/28
 */
@RestController
@RequestMapping("/article/comment")
public class ArticleCommentController {

    @Autowired
    ArticleCommentService articleCommentService;

    @Autowired
    Mapper dozerMapper;

    @GetMapping("/getOneById/{articleCommentId}")
    public ArticleCommentVO getOneById(@PathVariable("articleCommentId") Long articleCommentId) {
        ArticleCommentDTO articleCommentDTO =
                (ArticleCommentDTO) articleCommentService.getArticleCommentDTO(articleCommentId).getData();
        ArticleCommentVO articleCommentVO = dozerMapper.map(articleCommentDTO, ArticleCommentVO.class);
        return articleCommentVO;
    }

    @PostMapping("/getlistByQuery")
    public List<ArticleCommentVO> getlistByArticleIdAndQuery(@RequestBody @Validated ArticleCommentListAO articleCommentListAO) {
        List<ArticleCommentVO> articleCommentVOList = new ArrayList<>();
        //按条件分页返回文章所有评论
        List<ArticleCommentDTO> articleCommentDTOList;
        ArticleCommentQuery articleCommentQuery = articleCommentListAO.getArticleCommentQuery();
        if (articleCommentQuery != null)
            articleCommentDTOList =
                    (List<ArticleCommentDTO>) articleCommentService.listArticleCommentDTOsByQuery(articleCommentListAO.getPageNum(),
                            articleCommentListAO.getPageSize(), articleCommentQuery).getData();
        else
            articleCommentDTOList =
                    (List<ArticleCommentDTO>) articleCommentService.listArticleCommentDTOs(articleCommentListAO.getPageNum(),
                            articleCommentListAO.getPageSize()).getData();
        for (ArticleCommentDTO articleCommentDTO : articleCommentDTOList) {
            articleCommentVOList.add(dozerMapper.map(articleCommentDTO, ArticleCommentVO.class));
        }
        return articleCommentVOList;
    }

    @PostMapping("/save")
    public ArticleCommentVO save(@RequestBody @Validated ArticleCommentAO articleCommentAO) {
        ArticleCommentDTO articleCommentDTO = dozerMapper.map(articleCommentAO, ArticleCommentDTO.class);
        articleCommentDTO =
                (ArticleCommentDTO) articleCommentService.saveArticleCommentDTO(articleCommentDTO).getData();
        ArticleCommentVO articleCommentVO = dozerMapper.map(articleCommentDTO, ArticleCommentVO.class);
        return articleCommentVO;
    }

    @PutMapping("/clearOneById")
    public ArticleCommentVO clearOneById(@RequestParam("articleCommentId") Long articleCommentId) {
        ArticleCommentDTO articleCommentDTO =
                (ArticleCommentDTO) articleCommentService.clearArticleCommentDTO(articleCommentId).getData();
        ArticleCommentVO articleCommentVO = dozerMapper.map(articleCommentDTO, ArticleCommentVO.class);
        return articleCommentVO;
    }

    @PutMapping("/clearListByIds")
    public List<ArticleCommentVO> clearListByIds(@RequestParam("articleCommentIds") List<Long> articleCommentIds) {
        ArticleCommentDTO articleCommentDTO;
        ArticleCommentVO articleCommentVO;
        List<ArticleCommentVO> articleCommentVOList = new ArrayList<>();
        for (Long articleCommentId : articleCommentIds) {
            articleCommentDTO =
                    (ArticleCommentDTO) articleCommentService.clearArticleCommentDTO(articleCommentId).getData();
            articleCommentVO = dozerMapper.map(articleCommentDTO, ArticleCommentVO.class);
            articleCommentVOList.add(articleCommentVO);
        }
        return articleCommentVOList;
    }
}
