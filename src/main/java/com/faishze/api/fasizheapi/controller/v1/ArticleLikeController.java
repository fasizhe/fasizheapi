package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.pojo.ao.ArticleLikeAO;
import com.faishze.api.fasizheapi.pojo.dto.ArticleLikeDTO;
import com.faishze.api.fasizheapi.pojo.vo.ArticleLikeVO;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.ArticleLikeService;
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
 * @since 2019/11/3
 */
@RestController("/article/like")
public class ArticleLikeController {

    @Autowired
    ArticleLikeService articleLikeService;

    @Autowired
    Mapper dozerMapper;

    @PostMapping("/save")
    public ArticleLikeVO save(@RequestBody @Validated ArticleLikeAO articleLikeAO) {
        ArticleLikeDTO articleLikeDTO = dozerMapper.map(articleLikeAO,
                ArticleLikeDTO.class);
        articleLikeDTO =
                (ArticleLikeDTO) articleLikeService.saveArticleLikeDTO(articleLikeDTO).getData();
        ArticleLikeVO articleLikeVO = dozerMapper.map(articleLikeDTO, ArticleLikeVO.class);
        return articleLikeVO;
    }

    @Delete("/delete")
    public Result delete(@RequestParam("articleLikeId") Long id) {
        return articleLikeService.deleteArticleLikeDTO(id);
    }

    @GetMapping("/getListByQuery")
    public List<ArticleLikeVO> getListByQuery(@RequestParam("articleLikeAOs") List<ArticleLikeAO> articleLikeAOS) {
        List<ArticleLikeVO> articleLikeVOS = new ArrayList<>();
        ArticleLikeVO articleLikeVO;
        ArticleLikeDTO articleLikeDTO;
        Result result;
        for (ArticleLikeAO articleLikeAO : articleLikeAOS) {
            //articleLikeDTO=dozerMapper.map(articleLikeAO,ArticleLikeDTO.class);
            result=articleLikeService.getArticleLikeDTOsByUserIdAndArticleId(articleLikeAO.getUserId(),
                    articleLikeAO.getArticleId());
            if (result.isSuccess()){
                articleLikeDTO= (ArticleLikeDTO) result.getData();
            }else{
                articleLikeDTO=new ArticleLikeDTO();
                articleLikeDTO.setId((long) -1);
            }
            articleLikeVO=dozerMapper.map(articleLikeDTO,ArticleLikeVO.class);
            articleLikeVOS.add(articleLikeVO);
        }
        return articleLikeVOS;
    }
}
