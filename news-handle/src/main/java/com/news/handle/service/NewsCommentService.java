package com.news.handle.service;

import com.news.common.core.dto.PageData;
import com.news.common.project.dto.NewsCommentDto;
import com.news.common.project.dto.NewsDetailDto;

public interface NewsCommentService {
	public int insertComment(NewsCommentDto record);
	
	public PageData<NewsCommentDto> findPage(PageData<NewsCommentDto> commentPage, NewsDetailDto newsDetailDto);

	public PageData<NewsCommentDto> findCommentPage(PageData<NewsCommentDto> pageData, NewsCommentDto newsCommentDto);

	public int deleteNewsComment(String id);
}
