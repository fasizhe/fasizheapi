package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.TReport;
import java.util.List;

public interface TReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TReport record);

    TReport selectByPrimaryKey(Long id);

    List<TReport> selectAll();

    int updateByPrimaryKey(TReport record);
}