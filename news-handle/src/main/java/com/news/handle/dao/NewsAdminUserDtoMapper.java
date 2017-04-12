package com.news.handle.dao;

import org.springframework.stereotype.Repository;

import com.news.common.project.dto.NewsAdminUserDto;
@Repository("newsAdminUserDtoMapper")
public interface NewsAdminUserDtoMapper {
    NewsAdminUserDto getUserByName(String username);
}