package com.news.handle.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.common.core.dto.PageData;
import com.news.common.project.app.NewsTypeAppService;
import com.news.common.project.dto.NewsTypeDto;
import com.news.handle.service.NewsTypeService;
@Service("newsTypeAppService")
public class NewsTypeAppServiceImpl implements NewsTypeAppService {

	@Autowired
	private NewsTypeService newsTypeService;
	@Override
	public NewsTypeDto findNewsById(String id) {
		return newsTypeService.findNewsById(id);
	}

	@Override
	public int insertNewsTypeDto(NewsTypeDto record) {
		return newsTypeService.insertNewsTypeDto(record);
	}

	@Override
	public int updateNewsTypeDto(NewsTypeDto record) {
		return newsTypeService.updateNewsTypeDto(record);
	}

	@Override
	public PageData<NewsTypeDto> findPageWithType(PageData<NewsTypeDto> pageData, NewsTypeDto newsTypeDto) {
		return newsTypeService.findPageWithType(pageData, newsTypeDto);
	}

	@Override
	public int deleteNewsTypeDto(String id) {
		return newsTypeService.deleteNewsTypeDto(id);
	}

	@Override
	public NewsTypeDto findByName(String name) {
		return newsTypeService.findByName(name);
	}

	@Override
	public List<NewsTypeDto> listAllTypes() {
		return newsTypeService.listAllTypes();
	}

}
