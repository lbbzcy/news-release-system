package com.news.frontend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.common.core.dto.PageData;
import com.news.common.project.app.NewsDetailAppService;
import com.news.common.project.dto.NewsDetailDto;
import com.news.frontend.common.BaseController;

@Controller
@RequestMapping("/media")
public class MediaController extends BaseController{
	
	@Autowired
	private NewsDetailAppService newsDetailAppService;
	
	@RequestMapping("/index")
	public String toMedia(Model model,HttpServletRequest request){
		getAllNewsType(model);
		getHotAndPicNews(model);
		PageData<NewsDetailDto> picNewsList = new PageData<NewsDetailDto>();
		picNewsList.setPageSize(8);
		picNewsList = newsDetailAppService.getPicNews(picNewsList);
		setPagination(model, picNewsList, request);
		model.addAttribute("mideaNewsList", picNewsList.getRows());
		return "/media/index";
	}
	@RequestMapping("/item")
	public String MediaItem(Model model){
		getAllNewsType(model);
		getHotAndPicNews(model);
		return "/media/item";
	}
}
