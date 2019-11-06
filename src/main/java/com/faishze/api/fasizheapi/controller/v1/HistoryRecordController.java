package com.faishze.api.fasizheapi.controller.v1;

import com.faishze.api.fasizheapi.pojo.ao.HistoryRecordListAO;
import com.faishze.api.fasizheapi.pojo.do0.Article;
import com.faishze.api.fasizheapi.pojo.dto.HistoryRecordDTO;
import com.faishze.api.fasizheapi.pojo.vo.HistoryRecordAboutArticleVO;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.HistoryRecordService;
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
 * @since 2019/11/5
 */
@RestController
@RequestMapping("/historyRecoed")
public class HistoryRecordController {

    @Autowired
    HistoryRecordService historyRecordService;

    @Autowired
    Mapper dozerMapper;

    @PostMapping("/getListByQuery")
    public List<HistoryRecordAboutArticleVO> getListByQuery(@RequestBody @Validated HistoryRecordListAO historyRecordListAO) {
        List<HistoryRecordDTO> historyRecordDTOS= (List<HistoryRecordDTO>) historyRecordService.listHistoryRecordDTOAboutArticleByUserId(historyRecordListAO.getUserId(),
                historyRecordListAO.getPageNum(), historyRecordListAO.getPageSize()).getData();
        List<HistoryRecordAboutArticleVO> historyRecordVOS=new ArrayList<>();
        HistoryRecordAboutArticleVO historyRecordAboutArticleVO;
        for (HistoryRecordDTO historyRecordDTO : historyRecordDTOS) {
            historyRecordAboutArticleVO=dozerMapper.map(historyRecordDTO,HistoryRecordAboutArticleVO.class);
            historyRecordAboutArticleVO.setArticle((Article) historyRecordDTO.getData());
            historyRecordVOS.add(historyRecordAboutArticleVO);
        }
        return historyRecordVOS;
    }

    @DeleteMapping("/deleteListByIds")
    public Result deleteByIds(@RequestParam("historyRecordIds") List<Long> historyRecordIds){
        for (Long historyRecordId : historyRecordIds) {
            historyRecordService.deleteHistoryRecordDTO(historyRecordId);
        }
        return Result.success();
    }
}
