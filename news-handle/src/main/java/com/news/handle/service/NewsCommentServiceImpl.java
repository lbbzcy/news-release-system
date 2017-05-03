package com.news.handle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.common.core.dto.PageData;
import com.news.common.project.dto.NewsCommentDto;
import com.news.common.project.dto.NewsDetailDto;
import com.news.handle.dao.NewsCommentDtoMapper;

@Service("newsCommentService")
public class NewsCommentServiceImpl implements NewsCommentService {
	@Autowired
	private NewsCommentDtoMapper newsCommentDtoMapper;
	@Override
	public int insertComment(NewsCommentDto record) {
		return newsCommentDtoMapper.insert(record);
	}
	@Override
	public PageData<NewsCommentDto> findPage(PageData<NewsCommentDto> pageData, NewsDetailDto newsDetailDto) {
		PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize());
		List<NewsCommentDto> findPage = newsCommentDtoMapper.findPage(newsDetailDto);
		if(null!=findPage){
			for(NewsCommentDto dto : findPage){
				generateChildren(dto);
			}
		}
		Page<NewsCommentDto> page = (Page<NewsCommentDto>) findPage;
		pageData.setTotal(page.getTotal());
		pageData.setRows(findPage);
		return pageData;
	}
	public void generateChildren(NewsCommentDto dto) {
		List<NewsCommentDto> replyList = newsCommentDtoMapper.findReply(dto);
		if(null!=replyList){
			dto.setChildren(replyList);
			for(NewsCommentDto replyDto : replyList){
				generateChildren(replyDto);
			}
		}
	}
	@Override
	
	public PageData<NewsCommentDto> findCommentPage(PageData<NewsCommentDto> pageData, NewsCommentDto newsCommentDto) {
		PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize());
		List<NewsCommentDto> findPage = newsCommentDtoMapper.findCommentPage(newsCommentDto);
		Page<NewsCommentDto> page = (Page<NewsCommentDto>) findPage;
		pageData.setTotal(page.getTotal());
		pageData.setRows(findPage);
		return pageData;
	}
	@Override
	public int deleteNewsComment(String id) {
		return newsCommentDtoMapper.deleteByPrimaryKey(id);
	}

}
