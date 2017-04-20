package com.news.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {
	@RequestMapping("/index")
	public String toIndex(){
		return "/main/index";
	}
	@RequestMapping("/main_news")
	public String toMainNews(){
		return "/main/main_news";
	}
}
