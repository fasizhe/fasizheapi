package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.pojo.ao.ArticleCommentReplyAO;
import com.faishze.api.fasizheapi.pojo.ao.ArticleCommentReplyListAO;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCommentReplyDTO;
import com.faishze.api.fasizheapi.pojo.vo.ArticleCommentReplyVO;
import com.faishze.api.fasizheapi.query.ArticleCommentReplyQuery;
import com.faishze.api.fasizheapi.service.ArticleCommentReplyService;
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
 * @since 2019/11/1
 */
@RestController
@RequestMapping("/article/comment/reply")
public class ArticleCommentReplyController {

    @Autowired
    ArticleCommentReplyService articleCommentReplyService;

    @Autowired
    Mapper dozerMapper;

    @GetMapping("/getOneById/{articleCommentReplyId}")
    public ArticleCommentReplyVO getOneById(@PathVariable("articleCommentReplyId") Long articleCommentReplyId) {
        ArticleCommentReplyDTO articleCommentReplyDTO =
                (ArticleCommentReplyDTO) articleCommentReplyService.getArticleCommentReplyDTO(articleCommentReplyId).getData();
        ArticleCommentReplyVO articleCommentReplyVO = dozerMapper.map(articleCommentReplyDTO, ArticleCommentReplyVO.class);
        return articleCommentReplyVO;
    }

    @GetMapping("/getlistByQuery")
    public List<ArticleCommentReplyVO> getlistByArticleIdAndQuery(@RequestBody @Validated ArticleCommentReplyListAO articleCommentReplyListAO) {
        List<ArticleCommentReplyVO> articleCommentReplyVOList = new ArrayList<>();
        //按条件分页返回文章相应评论的所有回复
        List<ArticleCommentReplyDTO> articleCommentReplyDTOList;
        ArticleCommentReplyQuery articleCommentReplyQuery = articleCommentReplyListAO.getArticleCommentReplyQuery();
        if (articleCommentReplyQuery != null)
            articleCommentReplyDTOList =
                    (List<ArticleCommentReplyDTO>) articleCommentReplyService.listArticleCommentReplyDTOsByQuery(articleCommentReplyListAO.getPageNum(),
                            articleCommentReplyListAO.getPageSize(), articleCommentReplyQuery).getData();
        else
            articleCommentReplyDTOList =
                    (List<ArticleCommentReplyDTO>) articleCommentReplyService.listArticleCommentReplyDTOs(articleCommentReplyListAO.getPageNum(),
                            articleCommentReplyListAO.getPageSize()).getData();
        for (ArticleCommentReplyDTO articleCommentReplyDTO : articleCommentReplyDTOList) {
            articleCommentReplyVOList.add(dozerMapper.map(articleCommentReplyDTO, ArticleCommentReplyVO.class));
        }
        return articleCommentReplyVOList;
    }

    @PostMapping("/save")
    public ArticleCommentReplyVO save(@RequestBody @Validated ArticleCommentReplyAO articleCommentReplyAO) {
        ArticleCommentReplyDTO articleCommentReplyDTO = dozerMapper.map(articleCommentReplyAO, ArticleCommentReplyDTO.class);
        articleCommentReplyDTO =
                (ArticleCommentReplyDTO) articleCommentReplyService.saveArticleCommentReplyDTO(articleCommentReplyDTO).getData();
        ArticleCommentReplyVO articleCommentReplyVO = dozerMapper.map(articleCommentReplyDTO, ArticleCommentReplyVO.class);
        return articleCommentReplyVO;
    }

    @PutMapping("/clearOneById")
    public ArticleCommentReplyVO clearOneById(@RequestParam("articleCommentReplyId") Long articleCommentReplyId) {
        ArticleCommentReplyDTO articleCommentReplyDTO =
                (ArticleCommentReplyDTO) articleCommentReplyService.clearArticleCommentReplyDTO(articleCommentReplyId).getData();
        ArticleCommentReplyVO articleCommentReplyVO = dozerMapper.map(articleCommentReplyDTO, ArticleCommentReplyVO.class);
        return articleCommentReplyVO;
    }

    @PutMapping("/clearListByIds")
    public List<ArticleCommentReplyVO> clearListByIds(@RequestParam("articleCommentReplyIds") List<Long> articleCommentReplyIds) {
        ArticleCommentReplyDTO articleCommentReplyDTO;
        ArticleCommentReplyVO articleCommentReplyVO;
        List<ArticleCommentReplyVO> articleCommentReplyVOList = new ArrayList<>();
        for (Long articleCommentReplyId : articleCommentReplyIds) {
            articleCommentReplyDTO =
                    (ArticleCommentReplyDTO) articleCommentReplyService.clearArticleCommentReplyDTO(articleCommentReplyId).getData();
            articleCommentReplyVO = dozerMapper.map(articleCommentReplyDTO, ArticleCommentReplyVO.class);
            articleCommentReplyVOList.add(articleCommentReplyVO);
        }
        return articleCommentReplyVOList;
    }
}
