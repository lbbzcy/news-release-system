package com.news.handle.dao;

import com.news.common.project.dto.NewsUserDto;

public interface NewsUserDtoMapper {
    int deleteByPrimaryKey(String id);

    int insert(NewsUserDto record);

    int insertSelective(NewsUserDto record);

    NewsUserDto selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NewsUserDto record);

    int updateByPrimaryKey(NewsUserDto record);
}