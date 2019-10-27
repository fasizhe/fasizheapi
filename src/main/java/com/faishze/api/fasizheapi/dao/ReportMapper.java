package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.Report;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface  ReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Report record);

    Report selectByPrimaryKey(Long id);

    List<Report> selectAll();

    int updateByPrimaryKey(Report record);
}