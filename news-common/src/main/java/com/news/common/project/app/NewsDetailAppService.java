package com.news.common.project.app;

import java.util.List;

import com.news.common.core.dto.PageData;
import com.news.common.project.dto.NewsDetailDto;

public interface NewsDetailAppService {
	public PageData<NewsDetailDto> findPageWithType(PageData<NewsDetailDto> pageData,NewsDetailDto newsDetailDto);

	public NewsDetailDto findNewsById(String id);

	public int insertNewsDetailDto(NewsDetailDto newsEntity);

	public int updateNewsDetailDto(NewsDetailDto newsEntity);

	public int deleteNewsDetailDto(String id);
	
	public PageData<NewsDetailDto> getHotNews(PageData<NewsDetailDto> hotNewsList);
	
	public PageData<NewsDetailDto> getPicNews(PageData<NewsDetailDto> picNewsList);

	public PageData<NewsDetailDto> getNewsByTypeId(PageData<NewsDetailDto> pageData, String typeid);

	public List<NewsDetailDto> getAllCollectNewsByUserId(String id);
}
