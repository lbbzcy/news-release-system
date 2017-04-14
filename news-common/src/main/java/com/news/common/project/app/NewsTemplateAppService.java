package com.news.common.project.app;

import java.util.List;

import com.news.common.core.dto.PageData;
import com.news.common.project.dto.NewsTemplateDto;

public interface NewsTemplateAppService {
	//根据name查找template
	public List<NewsTemplateDto> findByName(String name);
	//find template by id
	public NewsTemplateDto findById(String id);
	//添加template
	public int addTemplate(NewsTemplateDto template);
	//update template
	public int updateTemplate(NewsTemplateDto template);
	//delete template
	public int deleteTemplate(String id);
	//find templates divided pages
	public PageData<NewsTemplateDto> findPageWithType(PageData<NewsTemplateDto> pageData,NewsTemplateDto newsTemplateDto);
	public List<NewsTemplateDto> listAllTemplate();
}
