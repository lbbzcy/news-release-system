package com.news.common.project.app;

import java.util.List;

import com.news.common.core.dto.PageData;
import com.news.common.project.dto.NewsTypeDto;

public interface NewsTypeAppService {
	//根据类别id查找新闻类别
	public NewsTypeDto findNewsById(String id);
	//插入新闻类别
	public int insertNewsTypeDto(NewsTypeDto record);
	//更新新闻类别
	public int updateNewsTypeDto(NewsTypeDto record);
	//分页查询新闻类别
	public PageData<NewsTypeDto> findPageWithType(PageData<NewsTypeDto> pageData,NewsTypeDto newsTypeDto);
	//删除类别
	public int deleteNewsTypeDto(String id);
	//根据类别名称查询类别
	public List<NewsTypeDto> findByName(String name);
	
	public List<NewsTypeDto> listAllTypes();
}
