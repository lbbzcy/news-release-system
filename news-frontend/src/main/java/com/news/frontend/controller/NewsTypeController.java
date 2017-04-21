package com.news.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.frontend.common.BaseController;

@Controller
@RequestMapping("/newstype")
public class NewsTypeController extends BaseController{
	@RequestMapping("/index")
	public String toIndex(Model model){
		getAllNewsType(model);
		return "/newstype/news_type";
	}
}
