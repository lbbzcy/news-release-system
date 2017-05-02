package com.news.handle.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.common.core.dto.PageData;
import com.news.common.project.app.NewsCommentAppService;
import com.news.common.project.dto.NewsCommentDto;
import com.news.common.project.dto.NewsDetailDto;
import com.news.handle.service.NewsCommentService;
@Service("newsCommentAppService")
public class NewsCommentAppServiceImpl implements NewsCommentAppService {

	@Autowired
	private NewsCommentService newsCommentService;
	@Override
	public int insertComment(NewsCommentDto record) {
		return newsCommentService.insertComment(record);
	}
	@Override
	public PageData<NewsCommentDto> findPage(PageData<NewsCommentDto> commentPage, NewsDetailDto newsDetailDto) {
		return newsCommentService.findPage(commentPage,newsDetailDto);
	}

}
