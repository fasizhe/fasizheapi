package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.HistoryRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HistoryRecord record);

    HistoryRecord selectByPrimaryKey(Long id);

    List<HistoryRecord> selectAll();

    int updateByPrimaryKey(HistoryRecord record);
}