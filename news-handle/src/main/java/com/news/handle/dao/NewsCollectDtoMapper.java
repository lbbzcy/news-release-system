package com.news.handle.dao;

import org.springframework.stereotype.Repository;

import com.news.common.project.dto.NewsCollectDto;
@Repository("newsCollectDtoMapper")
public interface NewsCollectDtoMapper {
    int deleteByPrimaryKey(String id);

    int insert(NewsCollectDto record);

    int insertSelective(NewsCollectDto record);

    NewsCollectDto selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NewsCollectDto record);

    int updateByPrimaryKey(NewsCollectDto record);

	NewsCollectDto select(NewsCollectDto record);
}