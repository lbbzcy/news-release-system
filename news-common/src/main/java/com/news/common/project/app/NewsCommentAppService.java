package com.news.common.project.app;


import com.news.common.core.dto.PageData;
import com.news.common.project.dto.NewsCommentDto;
import com.news.common.project.dto.NewsDetailDto;

public interface NewsCommentAppService {
	public int insertComment(NewsCommentDto record);

	public PageData<NewsCommentDto> findPage(PageData<NewsCommentDto> commentPage, NewsDetailDto newsDetailDto);

	public PageData<NewsCommentDto> findCommentPage(PageData<NewsCommentDto> pageData, NewsCommentDto queryParam);

	public int deleteNewsCommentDto(String id);
	
}
