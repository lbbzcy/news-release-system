package com.news.handle.dao;

import java.util.List;

import com.news.common.project.dto.NewsCommentDto;
import com.news.common.project.dto.NewsDetailDto;

public interface NewsCommentDtoMapper {
    int deleteByPrimaryKey(String id);

    int insert(NewsCommentDto record);

    int insertSelective(NewsCommentDto record);

    NewsCommentDto selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NewsCommentDto record);

    int updateByPrimaryKey(NewsCommentDto record);

	List<NewsCommentDto> findPage(NewsDetailDto newsDetailDto);

	List<NewsCommentDto> findReply(NewsCommentDto dto);
}