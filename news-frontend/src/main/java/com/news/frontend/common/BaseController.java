package com.news.frontend.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.news.common.project.app.NewsTypeAppService;
import com.news.common.project.dto.NewsTypeDto;

public class BaseController {
	@Autowired 
	private NewsTypeAppService newsTypeAppService;
	
	protected void getAllNewsType(Model model) {
		List<NewsTypeDto> list = newsTypeAppService.listAllTypes();
		model.addAttribute("data", list);
	}
}
