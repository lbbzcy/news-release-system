package com.news.handle.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.news.common.project.dto.NewsDetailDto;
@Repository("newsDetailDtoMapper")
public interface NewsDetailDtoMapper {
    int deleteByPrimaryKey(String id);

    int insert(NewsDetailDto record);

    int insertSelective(NewsDetailDto record);

    NewsDetailDto selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NewsDetailDto record);

    int updateByPrimaryKeyWithBLOBs(NewsDetailDto record);

    int updateByPrimaryKey(NewsDetailDto record);
    
    List<NewsDetailDto> queryAllData(NewsDetailDto newsDetailDto);

	List<NewsDetailDto> getHotNews();

	List<NewsDetailDto> getPicNews();

	List<NewsDetailDto> getNewsByTypeId(String typeid);
}