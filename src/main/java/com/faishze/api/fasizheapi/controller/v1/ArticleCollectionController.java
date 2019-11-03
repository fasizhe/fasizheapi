package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.pojo.ao.ArticleCollectionAO;
import com.faishze.api.fasizheapi.pojo.ao.ArticleCollectionListAO;
import com.faishze.api.fasizheapi.pojo.dto.ArticleCollectionDTO;
import com.faishze.api.fasizheapi.pojo.vo.ArticleCollectionVO;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleCollectionService;
import org.apache.ibatis.annotations.Delete;
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
 * @since 2019/11/2
 */
@RestController("/article/collection")
public class ArticleCollectionController {

    @Autowired
    ArticleCollectionService articleCollectionService;

    @Autowired
    Mapper dozerMapper;

    @PostMapping("/save")
    public ArticleCollectionVO save(@RequestBody @Validated ArticleCollectionAO articleCollectionAO) {
        ArticleCollectionDTO articleCollectionDTO = dozerMapper.map(articleCollectionAO, ArticleCollectionDTO.class);
        articleCollectionDTO =
                (ArticleCollectionDTO) articleCollectionService.saveArticleCollectionDTO(articleCollectionDTO).getData();
        ArticleCollectionVO articleCollectionVO = dozerMapper.map(articleCollectionDTO, ArticleCollectionVO.class);
        return articleCollectionVO;
    }

    @GetMapping("/getListByUserId")
    public List<ArticleCollectionVO> getListByUserId(@RequestBody @Validated ArticleCollectionListAO articleCollectionListAO) {
        List<ArticleCollectionVO> articleCollectionVOS = new ArrayList<>();
        ArticleCollectionVO articleCollectionVO;
        List<ArticleCollectionDTO> articleCollectionDTOS =
                (List<ArticleCollectionDTO>) articleCollectionService.listArticleCollectionDTOsByCollectorId(
                        articleCollectionListAO.getUserId(), articleCollectionListAO.getPageNum(),
                        articleCollectionListAO.getPageSize()).getData();
        for (ArticleCollectionDTO articleCollectionDTO : articleCollectionDTOS) {
            articleCollectionVO=dozerMapper.map(articleCollectionDTO,ArticleCollectionVO.class);
            articleCollectionVOS.add(articleCollectionVO);
        }
        return articleCollectionVOS;
    }

    @Delete("/deleteById")
    public Result deleteById(@RequestParam("articleCollectionId")Long articleCollectionId){
        return articleCollectionService.deleteArticleCollection(articleCollectionId);
    }

    @Delete("/deleteByIds")
    public Result deleteByIds(@RequestParam("articleCollectionIds") List<Long> articleCollectionIds){
        for (Long articleCollectionId : articleCollectionIds) {
            articleCollectionService.deleteArticleCollection(articleCollectionId);
        }
        return Result.success();
    }
}
