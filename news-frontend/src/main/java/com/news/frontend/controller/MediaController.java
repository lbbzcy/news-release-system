package com.news.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/media")
public class MediaController {
	@RequestMapping("/index")
	public String toMedia(){
		return "/media/index";
	}
	@RequestMapping("/item")
	public String MediaItem(){
		return "/media/item";
	}
}
