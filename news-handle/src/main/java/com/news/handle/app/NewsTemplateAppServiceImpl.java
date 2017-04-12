package com.news.handle.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.common.core.dto.PageData;
import com.news.common.project.app.NewsTemplateAppService;
import com.news.common.project.dto.NewsTemplateDto;
import com.news.handle.service.NewsTemplateService;
@Service("newsTemplateAppService")
public class NewsTemplateAppServiceImpl implements NewsTemplateAppService {

	@Autowired
	private NewsTemplateService newsTemplateService;
	@Override
	public NewsTemplateDto findByName(String name) {
		return newsTemplateService.findByName(name);
	}

	@Override
	public NewsTemplateDto findById(String id) {
		return newsTemplateService.findById(id);
	}

	@Override
	public int addTemplate(NewsTemplateDto template) {
		return newsTemplateService.addTemplate(template);
	}

	@Override
	public int updateTemplate(NewsTemplateDto template) {
		return newsTemplateService.updateTemplate(template);
	}

	@Override
	public int deleteTemplate(String id) {
		return newsTemplateService.deleteTemplate(id);
	}

	@Override
	public PageData<NewsTemplateDto> findPageWithType(PageData<NewsTemplateDto> pageData,
			NewsTemplateDto newsTemplateDto) {
		return newsTemplateService.findPageWithType(pageData, newsTemplateDto);
	}

	@Override
	public List<NewsTemplateDto> listAllTemplate() {
		return newsTemplateService.listAllTemplate();
	}

}
