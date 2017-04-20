package com.news.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/newsdetail")
public class NewsDetailController {
	@RequestMapping("/index")
	public String toNewsDetail(){
		return "/newsdetail/index";
	}
}
