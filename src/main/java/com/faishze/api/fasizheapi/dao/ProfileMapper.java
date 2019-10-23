package com.faishze.api.fasizheapi.dao;

import com.faishze.api.fasizheapi.pojo.do0.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProfileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Profile record);

    Profile selectByPrimaryKey(Integer id);

    List<Profile> selectAll();

    int updateByPrimaryKey(Profile record);
}