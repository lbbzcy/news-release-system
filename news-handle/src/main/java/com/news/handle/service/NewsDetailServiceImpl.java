package com.news.handle.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.common.core.dto.PageData;
import com.news.common.project.dto.NewsCollectDto;
import com.news.common.project.dto.NewsDetailDto;
import com.news.common.project.dto.NewsTemplateDto;
import com.news.common.project.dto.NewsTypeDto;
import com.news.handle.common.Constants;
import com.news.handle.dao.NewsDetailDtoMapper;

@Service("newsDetailService")
@Transactional(value=Constants.TX_MANAGER_NEWS)
public class NewsDetailServiceImpl implements NewsDetailService {

	@Autowired 
	private NewsDetailDtoMapper newsDetailDtoMapper;
	@Autowired
	private NewsTypeService newsTypeService;
	@Autowired
	private NewsTemplateService newsTemplateService;
	@Autowired
	private NewsCollectService newsCollectService;
	@Override
	public PageData<NewsDetailDto> findPageWithType(PageData<NewsDetailDto> pageData, NewsDetailDto newsDetailDto) {
		PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize());
		List<NewsDetailDto> findListPage = newsDetailDtoMapper.queryAllData(newsDetailDto);
		ArrayList<String> newsTypes = new ArrayList<String>();
		ArrayList<String> templates = new ArrayList<String>();
		for (NewsDetailDto dto : findListPage) {
			newsTypes.add(dto.getTypeid());
			templates.add(dto.getTemplateid());
		}
		List<NewsTypeDto> newsTypeDtoList = null;
		List<NewsTemplateDto> newsTemplateDtoList = null;

		if(!newsTypes.isEmpty()){
			newsTypeDtoList=newsTypeService.getNewsByNewsTypes(newsTypes);
		}
		if(!templates.isEmpty()){
			newsTemplateDtoList=newsTemplateService.getNameByTemplate(templates);
		}
		if(newsTypeDtoList!=null  && !newsTypeDtoList.isEmpty()){
			HashMap<String, NewsTypeDto> newsTypeDtoMap = new HashMap<String,NewsTypeDto>();
			for (NewsTypeDto newsTypeDto : newsTypeDtoList) {
				newsTypeDtoMap.put(newsTypeDto.getId(), newsTypeDto);
			}
			for (NewsDetailDto dto : findListPage) {
				NewsTypeDto newsTypeDto = newsTypeDtoMap.get(dto.getTypeid());
				if(newsTypeDto!=null){
					dto.setTypename(newsTypeDto.getName());
				}
			}
		}
		if(newsTemplateDtoList!=null  && !newsTemplateDtoList.isEmpty()){
			HashMap<String, NewsTemplateDto> newsTemplateDtoMap = new HashMap<String,NewsTemplateDto>();
			for (NewsTemplateDto newsTemplateDto : newsTemplateDtoList) {
				newsTemplateDtoMap.put(newsTemplateDto.getId(), newsTemplateDto);
			}
			for (NewsDetailDto dto : findListPage) {
				NewsTemplateDto newsTemplateDto = newsTemplateDtoMap.get(dto.getTemplateid());
				if(newsTemplateDto!=null){
					dto.setTemplatename(newsTemplateDto.getName());
				}
			}
		}
		
		
		Page<NewsDetailDto> page = (Page<NewsDetailDto>) findListPage;
		pageData.setTotal(page.getTotal());
		pageData.setRows(findListPage);
		return pageData;
	}
	@Override
	public NewsDetailDto findNewsById(String id) {
		return newsDetailDtoMapper.selectByPrimaryKey(id);
	}
	@Override
	public int insertNewsDetailDto(NewsDetailDto newsEntity) {
		if(newsEntity != null){
			newsEntity.setTypename(newsTypeService.findNewsById(newsEntity.getTypeid()).getName());
			newsEntity.setTemplatename(newsTemplateService.findById(newsEntity.getTemplateid()).getName());
		}
		return newsDetailDtoMapper.insert(newsEntity);
	}
	@Override
	public int updateNewsDetailDto(NewsDetailDto newsEntity) {
		return newsDetailDtoMapper.updateByPrimaryKeySelective(newsEntity);
	}
	@Override
	public int deleteNewsDetailDto(String id) {
		return newsDetailDtoMapper.deleteByPrimaryKey(id);
	}
	@Override
	public PageData<NewsDetailDto> getHotNews(PageData<NewsDetailDto> pageData) {
		PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize());
		List<NewsDetailDto> findListPage = newsDetailDtoMapper.getHotNews();
		Page<NewsDetailDto> page = (Page<NewsDetailDto>) findListPage;
		pageData.setTotal(page.getTotal());
		pageData.setRows(findListPage);
		return pageData;
	}
	@Override
	public PageData<NewsDetailDto> getPicNews(PageData<NewsDetailDto> pageData) {
		PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize());
		List<NewsDetailDto> findListPage = newsDetailDtoMapper.getPicNews();
		Page<NewsDetailDto> page = (Page<NewsDetailDto>) findListPage;
		pageData.setTotal(page.getTotal());
		pageData.setRows(findListPage);
		return pageData;
	}
	@Override
	public PageData<NewsDetailDto> getNewsByTypeId(PageData<NewsDetailDto> pageData, String typeid) {
		PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize());
		List<NewsDetailDto> findListPage = newsDetailDtoMapper.getNewsByTypeId(typeid);
		Page<NewsDetailDto> page = (Page<NewsDetailDto>) findListPage;
		pageData.setTotal(page.getTotal());
		pageData.setRows(findListPage);
		return pageData;
	}
	@Override
	public List<NewsDetailDto> getAllCollectNewsByUserId(String id) {
		List<NewsDetailDto> list = new ArrayList<NewsDetailDto>();
		List<NewsCollectDto> collectList = newsCollectService.getByUserId(id);
		List<String> newsIdList = new ArrayList<String>();
		if(null!=collectList&&!collectList.isEmpty()){
			for(NewsCollectDto dto : collectList){
				newsIdList.add(dto.getNewsId());
			}
		}
		if(newsIdList!=null && !newsIdList.isEmpty()){
			list = newsDetailDtoMapper.getAllCollectNews(newsIdList);
		}
		return list;
	}

}
