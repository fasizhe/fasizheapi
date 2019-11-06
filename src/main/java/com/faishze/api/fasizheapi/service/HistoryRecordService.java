package com.faishze.api.fasizheapi.service;

import com.faishze.api.fasizheapi.pojo.do0.Article;
import com.faishze.api.fasizheapi.pojo.dto.HistoryRecordDTO;
import com.faishze.api.fasizheapi.result.Result;

/**
 * @author masonluo
 * @date 2019/10/27 5:10 PM
 */
public interface HistoryRecordService {

    Result deleteHistoryRecordDTO(Long id);

    Result saveHistoryRecordDTO(HistoryRecordDTO record);

    Result getHistoryRecordDTO(Long id);

    Result listHistoryRecordDTOs();

    Result updateHistoryRecordDTO(HistoryRecordDTO record);

    //updateTime降序
    Result listHistoryRecordDTOAboutArticleByUserId(Integer userId,Integer pageNum, Integer pageSize);

    Result saveHistoryRecordDTOAboutArticle(HistoryRecordDTO<Article> record);
}
