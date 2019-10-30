package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.pojo.ao.ArticleListAO;
import com.faishze.api.fasizheapi.pojo.ao.ArticleNewAO;
import com.faishze.api.fasizheapi.pojo.ao.ArticleOldAO;
import com.faishze.api.fasizheapi.pojo.dto.ArticleDTO;
import com.faishze.api.fasizheapi.pojo.vo.ArticleVO;
import com.faishze.api.fasizheapi.query.ArticleQuery;
import com.faishze.api.fasizheapi.service.ArticleService;
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
@RestController("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @Autowired
    Mapper dozerMapper;

    @GetMapping("/getOneById/{articleId}")
    public ArticleVO getOneById(@PathVariable("articleId") Integer articleId) {
        ArticleVO articleVO;
        ArticleDTO articleDTO = (ArticleDTO) articleService.getArticleDTO(articleId).getData();
        articleVO = dozerMapper.map(articleDTO, ArticleVO.class);
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
        articleDTO= (ArticleDTO) articleService.getArticleDTO(articleOldAO.getId()).getData();
        articleDTO.setType(articleOldAO.getType());
        articleDTO.setTitle(articleOldAO.getTitle());
        articleDTO.setContent(articleOldAO.getContent());
        articleDTO = (ArticleDTO) articleService.updateArticleDTO(articleDTO).getData();
        ArticleVO articleVO;
        articleVO = dozerMapper.map(articleDTO, ArticleVO.class);
        return articleVO;
    }
}