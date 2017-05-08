package com.news.handle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.common.project.dto.NewsCollectDto;
import com.news.handle.dao.NewsCollectDtoMapper;

@Service("newsCollectService")
public class NewsCollectServiceImpl implements NewsCollectService {
	@Autowired
	private NewsCollectDtoMapper newsCollectDtoMapper;
	@Override
	public int insert(NewsCollectDto record) {
		return newsCollectDtoMapper.insert(record);
	}
	@Override
	public NewsCollectDto select(NewsCollectDto record) {
		return newsCollectDtoMapper.select(record);
	}

}
