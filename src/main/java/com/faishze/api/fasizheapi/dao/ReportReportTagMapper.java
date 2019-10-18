package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ReportReportTag;
import java.util.List;

public interface ReportReportTagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReportReportTag record);

    ReportReportTag selectByPrimaryKey(Long id);

    List<ReportReportTag> selectAll();

    int updateByPrimaryKey(ReportReportTag record);
}