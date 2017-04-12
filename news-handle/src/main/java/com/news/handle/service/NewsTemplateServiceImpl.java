package com.news.handle.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.common.core.dto.PageData;
import com.news.common.project.dto.NewsTemplateDto;
import com.news.handle.common.Constants;
import com.news.handle.dao.NewsTemplateDtoMapper;
@Service("newsTemplateService")
@Transactional(value=Constants.TX_MANAGER_NEWS)
public class NewsTemplateServiceImpl implements NewsTemplateService {

	@Autowired
	private NewsTemplateDtoMapper newsTemplateDtoMapper;
	@Override
	public NewsTemplateDto findByName(String name) {
		return newsTemplateDtoMapper.findByName(name);
	}

	@Override
	public NewsTemplateDto findById(String id) {
		return newsTemplateDtoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int addTemplate(NewsTemplateDto template) {
		return newsTemplateDtoMapper.insertSelective(template);
	}

	@Override
	public int updateTemplate(NewsTemplateDto template) {
		return newsTemplateDtoMapper.updateByPrimaryKeySelective(template);
	}

	@Override
	public int deleteTemplate(String id) {
		return newsTemplateDtoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageData<NewsTemplateDto> findPageWithType(PageData<NewsTemplateDto> pageData,
			NewsTemplateDto newsTemplateDto) {
		PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize());
		List<NewsTemplateDto> findListPage = newsTemplateDtoMapper.queryAllData(newsTemplateDto);
		Page<NewsTemplateDto> page = (Page<NewsTemplateDto>) findListPage;
		pageData.setTotal(page.getTotal());
		pageData.setRows(findListPage);
		return pageData;
	}

	@Override
	public List<NewsTemplateDto> listAllTemplate() {
		return newsTemplateDtoMapper.listAllTemplate();
	}

	@Override
	public List<NewsTemplateDto> getNameByTemplate(ArrayList<String> templates) {
		return newsTemplateDtoMapper.findNameByTmplate(templates);
	}

}
