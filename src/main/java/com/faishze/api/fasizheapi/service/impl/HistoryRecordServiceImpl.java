package com.faishze.api.fasizheapi.service.impl;

import com.faishze.api.fasizheapi.dao.HistoryRecordMapper;
import com.faishze.api.fasizheapi.pojo.do0.Article;
import com.faishze.api.fasizheapi.pojo.do0.HistoryRecord;
import com.faishze.api.fasizheapi.pojo.do0.entity.HistoryRecordAboutArticleEntity;
import com.faishze.api.fasizheapi.pojo.dto.HistoryRecordDTO;
import com.faishze.api.fasizheapi.result.Result;
import com.faishze.api.fasizheapi.service.HistoryRecordService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author masonluo
 * @date 2019/10/27 11:28 PM
 */
@Service
@Transactional
public class HistoryRecordServiceImpl implements HistoryRecordService {

    @Autowired
    HistoryRecordMapper historyRecordMapper;

    @Autowired
    Mapper dozerMapper;

    @Override
    public Result deleteHistoryRecordDTO(Long id) {
        historyRecordMapper.deleteHistoryRecord(id);
        return Result.success();
    }

    @Override
    public Result saveHistoryRecordDTO(HistoryRecordDTO record) {
        return null;
    }

    @Override
    public Result getHistoryRecordDTO(Long id) {
        return null;
    }

    @Override
    public Result listHistoryRecordDTOs() {
        return null;
    }

    @Override
    public Result updateHistoryRecordDTO(HistoryRecordDTO record) {
        return null;
    }

    @Override
    public Result listHistoryRecordDTOAboutArticleByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<HistoryRecordAboutArticleEntity> historyRecordAboutArticleEntities =
                historyRecordMapper.listHistoryRecordAboutArticleEntitysByUserId(userId);
        Page<HistoryRecordDTO<Article>> historyRecordDTOS = new Page<>();
        HistoryRecordDTO<Article> historyRecordDTO;
        for (HistoryRecordAboutArticleEntity historyRecordAboutArticleEntity : historyRecordAboutArticleEntities) {
            historyRecordDTO=dozerMapper.map(historyRecordAboutArticleEntity, HistoryRecordDTO.class);
            historyRecordDTO.setData(historyRecordAboutArticleEntity.getArticle());
            historyRecordDTOS.add(historyRecordDTO);
        }
        return Result.success(historyRecordDTOS);
    }

    @Override
    public Result saveHistoryRecordDTOAboutArticle(HistoryRecordDTO<Article> record) {
        HistoryRecord historyRecord = dozerMapper.map(record, HistoryRecord.class);
        historyRecord.setTypeId(record.getData().getId().longValue());
        historyRecordMapper.saveHistoryRecord(historyRecord);
        record.setId(historyRecord.getId());
        return Result.success(record);
    }
}
