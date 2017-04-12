package com.news.handle.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.news.common.project.dto.NewsTypeDto;

@Repository("newsTypeDtoMapper")
public interface NewsTypeDtoMapper {
    int deleteByPrimaryKey(String id);

    int insert(NewsTypeDto record);

    int insertSelective(NewsTypeDto record);

    NewsTypeDto selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NewsTypeDto record);

    int updateByPrimaryKey(NewsTypeDto record);
    
    NewsTypeDto findByName(String name);
    
    List<NewsTypeDto> queryAllData(NewsTypeDto newsTypeDto);
    
    List<NewsTypeDto> listAllTypes();

	List<NewsTypeDto> findNewsByNewsTypes(ArrayList<String> newsTypes);
}