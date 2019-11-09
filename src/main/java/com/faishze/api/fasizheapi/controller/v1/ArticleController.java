package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.constant.HistoryRecordType;
import com.faishze.api.fasizheapi.pojo.ao.ArticleListAO;
import com.faishze.api.fasizheapi.pojo.ao.ArticleNewAO;
import com.faishze.api.fasizheapi.pojo.ao.ArticleOldAO;
import com.faishze.api.fasizheapi.pojo.do0.Article;
import com.faishze.api.fasizheapi.pojo.dto.ArticleDTO;
import com.faishze.api.fasizheapi.pojo.dto.HistoryRecordDTO;
import com.faishze.api.fasizheapi.pojo.vo.ArticleVO;
import com.faishze.api.fasizheapi.query.ArticleQuery;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleService;
import com.faishze.api.fasizheapi.service.HistoryRecordService;
import com.faishze.api.fasizheapi.service.UserService;
import com.github.pagehelper.Page;
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
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    HistoryRecordService historyRecordService;

    @Autowired
    UserService userService;

    @Autowired
    Mapper dozerMapper;

    @GetMapping("/getOneById/{articleId}")
    public ArticleVO getOneById(@PathVariable("articleId") Integer articleId, @RequestParam("userId") Integer userId) {
        ArticleVO articleVO;
        //文章浏览数+1
        articleService.riseViewNum(articleId);
        ArticleDTO articleDTO = (ArticleDTO) articleService.getArticleDTO(articleId).getData();
        articleVO = dozerMapper.map(articleDTO, ArticleVO.class);

        //添加历史记录
        HistoryRecordDTO<Article> historyRecordDTO = new HistoryRecordDTO();
        Article article = new Article();
        article.setId(articleId);
        historyRecordDTO.setData(article);
        historyRecordDTO.setType(HistoryRecordType.ARTICLE);
        historyRecordDTO.setUserId(userId);
        historyRecordService.saveHistoryRecordDTOAboutArticle(historyRecordDTO);

        return articleVO;
    }

    @PostMapping("/getListByQuery")
    public List<ArticleVO> getListByQuery(@RequestBody @Validated ArticleListAO articleListAO) {
        List<ArticleVO> articleVOS = new ArrayList<>();
        List<ArticleDTO> articleDTOS;
        ArticleQuery articleQuery = articleListAO.getArticleQuery();
        if (articleQuery != null)
            articleDTOS = (Page<ArticleDTO>) articleService.listArticleDTOsByQuery(articleListAO.getPageNum(),
                    articleListAO.getPageSize(),
                    articleQuery).getData();
        else
            articleDTOS = (Page<ArticleDTO>) articleService.listArticleDTOs(articleListAO.getPageNum(),
                    articleListAO.getPageSize()).getData();
        for (ArticleDTO articleDTO : articleDTOS) {
            articleVOS.add(dozerMapper.map(articleDTO, ArticleVO.class));
        }
        return articleVOS;
    }

    @PostMapping("/save")
    public ArticleVO save(@RequestBody @Validated ArticleNewAO articleNewAO) {
        //AO转DTO
        ArticleDTO articleDTO;
        articleDTO = dozerMapper.map(articleNewAO, ArticleDTO.class);
        articleDTO = (ArticleDTO) articleService.saveArticleDTO(articleDTO).getData();
        ArticleVO articleVO;
        articleVO = dozerMapper.map(articleDTO, ArticleVO.class);
        return articleVO;
    }

    @PutMapping("/update")
    public ArticleVO update(@RequestBody @Validated ArticleOldAO articleOldAO) {
        //AO转DTO,旧的表单，新的dto
        ArticleDTO articleDTO;
        articleDTO = (ArticleDTO) articleService.getArticleDTO(articleOldAO.getId()).getData();
        articleDTO.setType(articleOldAO.getType());
        articleDTO.setTitle(articleOldAO.getTitle());
        articleDTO.setContent(articleOldAO.getContent());
        articleDTO = (ArticleDTO) articleService.updateArticleDTO(articleDTO).getData();
        ArticleVO articleVO;
        articleVO = dozerMapper.map(articleDTO, ArticleVO.class);
        return articleVO;
    }

    @DeleteMapping("/deleteOneById")
    public Result deleteOneById(@RequestParam("articleId") Integer articleId) {
        articleService.deleteArticleDTO(articleId);
        return Result.success();
    }

    @DeleteMapping("/deleteListByIds")
    public Result deleteListByIds(@RequestParam("articleIds") List<Integer> articleIds) {
        for (Integer articleId : articleIds) {
            articleService.deleteArticleDTO(articleId);
        }
        return Result.success();
    }

    @PutMapping("/banOneById")
    public Result banOneById(@RequestParam("articleId") Integer articleId) {
        articleService.banArticleDTO(articleId);
        return Result.success();
    }

    @PutMapping("/banListByIds")
    public Result banListByIds(@RequestParam("articleIds") List<Integer> articleIds) {
        for (Integer articleId : articleIds) {
            articleService.banArticleDTO(articleId);
        }
        return Result.success();
    }
}
