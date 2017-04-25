package com.news.handle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.news.common.project.dto.NewsUserDto;

@Repository("newsUserDtoMapper")
public interface NewsUserDtoMapper {
    int deleteByPrimaryKey(String id);

    int insert(NewsUserDto record);

    int insertSelective(NewsUserDto record);

    NewsUserDto selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NewsUserDto record);

    int updateByPrimaryKey(NewsUserDto record);
    
    List<NewsUserDto> getUserByName(String username);
    
    List<NewsUserDto> getUserByMobile(String mobile);
    
    List<NewsUserDto> getUserByEmail(String email);

	List<NewsUserDto> queryAllData(NewsUserDto queryParam);

	NewsUserDto getUserByAuth(@Param("auth")String auth, @Param("password")String password);
}