package com.news.handle.service;

import com.news.common.core.dto.PageData;
import com.news.common.project.dto.NewsDetailDto;

public interface NewsDetailService {
	public PageData<NewsDetailDto> findPageWithType(PageData<NewsDetailDto> pageData,NewsDetailDto newsDetailDto);
	public NewsDetailDto findNewsById(String id);
	public int insertNewsDetailDto(NewsDetailDto newsEntity);

	public int updateNewsDetailDto(NewsDetailDto newsEntity);
	
	public int deleteNewsDetailDto(String id);
}
