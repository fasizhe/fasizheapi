package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.dao.query.ArticleQuery;
import com.faishze.api.fasizheapi.pojo.dto.ArticleDTO;
import com.faishze.api.fasizheapi.pojo.vo.ArticleVO;
import com.faishze.api.fasizheapi.service.ArticleService;
import com.faishze.api.fasizheapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getOneById/{articleId}")
    public ArticleVO getOneById(@PathVariable("articleId") Integer articleId){
        ArticleVO articleVO=new ArticleVO();
        ArticleDTO articleDTO=articleService.getArticleDTO(articleId);
        articleVO.setArticleDTO(articleDTO);
        System.out.println(articleVO);
        return articleVO;
    }

    @GetMapping("/getListByQuery")
    public List<ArticleVO> getListByQuery(@RequestParam("pageNum")Integer pageNum,
                                          @RequestParam("pageSize")Integer pageSize,
                                          @RequestParam("query")ArticleQuery articleQuery){
        //按条件分页返回
        return null;
    }
}
