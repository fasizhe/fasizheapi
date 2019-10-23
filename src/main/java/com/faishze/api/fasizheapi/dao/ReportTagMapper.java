package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.ReportTag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportTagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReportTag record);

    ReportTag selectByPrimaryKey(Long id);

    List<ReportTag> selectAll();

    int updateByPrimaryKey(ReportTag record);
}