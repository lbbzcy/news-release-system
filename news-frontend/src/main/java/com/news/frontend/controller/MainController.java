package com.news.frontend.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.frontend.common.BaseController;


@Controller
@RequestMapping("/main")
public class MainController extends BaseController{
	
	@RequestMapping("/index")
	public String toIndex(Model model){
		getAllNewsType(model);
		return "/main/index";
	}
	@RequestMapping("/main_news")
	public String toMainNews(Model model){
		getAllNewsType(model);
		return "/main/main_news";
	}
}
