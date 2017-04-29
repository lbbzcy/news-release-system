package com.news.handle.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.common.core.dto.PageData;
import com.news.common.project.app.NewsDetailAppService;
import com.news.common.project.dto.NewsDetailDto;
import com.news.handle.service.NewsDetailService;
@Service("newsDetailAppService")
public class NewsDetailAppServiceImpl implements NewsDetailAppService {
	@Autowired
	private NewsDetailService newsDetailService;
	@Override
	public PageData<NewsDetailDto> findPageWithType(PageData<NewsDetailDto> pageData, NewsDetailDto newsDetailDto) {
		return newsDetailService.findPageWithType(pageData, newsDetailDto);
	}
	@Override
	public NewsDetailDto findNewsById(String id) {
		return newsDetailService.findNewsById(id);
	}
	@Override
	public int insertNewsDetailDto(NewsDetailDto newsEntity) {
		return newsDetailService.insertNewsDetailDto(newsEntity);
	}
	@Override
	public int updateNewsDetailDto(NewsDetailDto newsEntity) {
		return newsDetailService.updateNewsDetailDto(newsEntity);
	}
	@Override
	public int deleteNewsDetailDto(String id) {
		return newsDetailService.deleteNewsDetailDto(id);
	}
	@Override
	public PageData<NewsDetailDto> getHotNews(PageData<NewsDetailDto> hotNewsList) {
		return newsDetailService.getHotNews(hotNewsList);
	}
	@Override
	public PageData<NewsDetailDto> getPicNews(PageData<NewsDetailDto> picNewsList) {
		return newsDetailService.getPicNews(picNewsList);
	}
	@Override
	public PageData<NewsDetailDto> getNewsByTypeId(PageData<NewsDetailDto> pageData, String typeid) {
		return newsDetailService.getNewsByTypeId(pageData,typeid);
	}

}
