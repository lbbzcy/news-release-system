package com.news.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.frontend.common.BaseController;

@Controller
@RequestMapping("/media")
public class MediaController extends BaseController{
	@RequestMapping("/index")
	public String toMedia(Model model){
		getAllNewsType(model);
		return "/media/index";
	}
	@RequestMapping("/item")
	public String MediaItem(Model model){
		getAllNewsType(model);
		return "/media/item";
	}
}
