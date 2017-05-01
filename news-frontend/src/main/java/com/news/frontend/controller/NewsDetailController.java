package com.news.frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.news.common.core.dto.PageData;
import com.news.common.project.app.NewsDetailAppService;
import com.news.common.project.dto.NewsDetailDto;
import com.news.frontend.common.BaseController;

@Controller
@RequestMapping("/newsdetail")
public class NewsDetailController extends BaseController{
	@Autowired
	private NewsDetailAppService newsDetailAppService;
	
	@RequestMapping("/index")
	public String toNewsDetail(Model model,@RequestParam(value="newsid") String newsid){
		getAllNewsType(model);
		getHotAndPicNews(model);
		System.out.println("新闻的ID为:"+newsid);
		//获取新闻详情
		NewsDetailDto newsDetailDto = newsDetailAppService.findNewsById(newsid);
		model.addAttribute("newsItem",newsDetailDto);
		//获取新闻相关评论
		
		//浏览数量增加
		newsDetailDto.setViewnum(newsDetailDto.getViewnum()+1);
		newsDetailAppService.updateNewsDetailDto(newsDetailDto);
		//获取相关新闻
		PageData<NewsDetailDto> pageData = new PageData<NewsDetailDto>();
		pageData.setPageSize(4);
		pageData = newsDetailAppService.findPageWithType(pageData, newsDetailDto);
		List<NewsDetailDto>  relatedNews = pageData.getRows();
		model.addAttribute("relatedNews", relatedNews);
		return "/newsdetail/index";
	}
}
