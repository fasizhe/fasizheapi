package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.THistoryRecord;
import java.util.List;

public interface THistoryRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(THistoryRecord record);

    THistoryRecord selectByPrimaryKey(Long id);

    List<THistoryRecord> selectAll();

    int updateByPrimaryKey(THistoryRecord record);
}