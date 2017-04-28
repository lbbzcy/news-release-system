package com.news.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.frontend.common.BaseController;

@Controller
@RequestMapping("/newsdetail")
public class NewsDetailController extends BaseController{
	@RequestMapping("/index")
	public String toNewsDetail(Model model){
		getAllNewsType(model);
		getHotAndPicNews(model);
		return "/newsdetail/index";
	}
}
