package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.TReportReportTag;
import java.util.List;

public interface TReportReportTagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TReportReportTag record);

    TReportReportTag selectByPrimaryKey(Long id);

    List<TReportReportTag> selectAll();

    int updateByPrimaryKey(TReportReportTag record);
}