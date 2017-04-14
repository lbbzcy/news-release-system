package com.news.handle.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.common.core.dto.PageData;
import com.news.common.project.dto.NewsTypeDto;
import com.news.handle.common.Constants;
import com.news.handle.dao.NewsTypeDtoMapper;
@Service("newsTypeService")
@Transactional(value=Constants.TX_MANAGER_NEWS)
public class NewsTypeServiceImpl implements NewsTypeService {

	@Autowired
	private NewsTypeDtoMapper newsTypeDtoMapper;
	@Override
	public NewsTypeDto findNewsById(String id) {
		return newsTypeDtoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertNewsTypeDto(NewsTypeDto record) {
		return newsTypeDtoMapper.insert(record);
	}

	@Override
	public int updateNewsTypeDto(NewsTypeDto record) {
		return newsTypeDtoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public PageData<NewsTypeDto> findPageWithType(PageData<NewsTypeDto> pageData, NewsTypeDto newsTypeDto) {
		PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize());
		List<NewsTypeDto> findListPage = newsTypeDtoMapper.queryAllData(newsTypeDto);
		Page<NewsTypeDto> page = (Page<NewsTypeDto>) findListPage;
		pageData.setTotal(page.getTotal());
		pageData.setRows(findListPage);
		return pageData;
	}

	@Override
	public int deleteNewsTypeDto(String id) {
		return newsTypeDtoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<NewsTypeDto> findByName(String name) {
		return newsTypeDtoMapper.findByName(name);
	}

	@Override
	public List<NewsTypeDto> listAllTypes() {
		return newsTypeDtoMapper.listAllTypes();
	}

	@Override
	public List<NewsTypeDto> getNewsByNewsTypes(ArrayList<String> newsTypes) {
		return newsTypeDtoMapper.findNewsByNewsTypes(newsTypes);
	}

}
