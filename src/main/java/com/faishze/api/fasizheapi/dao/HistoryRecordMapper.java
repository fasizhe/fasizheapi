package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.HistoryRecord;
import java.util.List;

public interface HistoryRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HistoryRecord record);

    HistoryRecord selectByPrimaryKey(Long id);

    List<HistoryRecord> selectAll();

    int updateByPrimaryKey(HistoryRecord record);
}