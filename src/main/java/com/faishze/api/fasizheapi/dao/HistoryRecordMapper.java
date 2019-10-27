package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.HistoryRecord;
import com.faishze.api.fasizheapi.pojo.do0.entity.HistoryRecordAboutArticleEntity;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryRecordMapper {
    int deleteHistoryRecord(Long id);

    int saveHistoryRecord(HistoryRecord record);

    HistoryRecord getHistoryRecord(Long id);

    Page<HistoryRecord> listHistoryRecords();

    int updateHistoryRecord(HistoryRecord record);

    //updateTime降序
    Page<HistoryRecordAboutArticleEntity> listHistoryRecordAboutArticleEntitysByUserId(Integer userId);
}