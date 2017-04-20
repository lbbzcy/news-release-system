package com.news.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/newstype")
public class NewsTypeController {
	@RequestMapping("/index")
	public String toIndex(){
		return "/newstype/news_type";
	}
}
