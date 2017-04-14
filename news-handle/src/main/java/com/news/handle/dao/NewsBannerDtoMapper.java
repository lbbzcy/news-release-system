package com.news.handle.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.news.common.project.dto.NewsBannerDto;
@Repository("newsBannerDtoMapper")
public interface NewsBannerDtoMapper {
    int deleteByPrimaryKey(String id);

    int insert(NewsBannerDto record);

    int insertSelective(NewsBannerDto record);

    NewsBannerDto selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NewsBannerDto record);

    int updateByPrimaryKey(NewsBannerDto record);
    
    List<NewsBannerDto> queryAllData(NewsBannerDto newsBannerDto);

	List<NewsBannerDto> findByNewsId(String id);
}