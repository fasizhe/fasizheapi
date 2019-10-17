package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.TReportTag;
import java.util.List;

public interface TReportTagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TReportTag record);

    TReportTag selectByPrimaryKey(Long id);

    List<TReportTag> selectAll();

    int updateByPrimaryKey(TReportTag record);
}