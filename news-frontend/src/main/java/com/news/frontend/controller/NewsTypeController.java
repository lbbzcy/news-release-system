package com.news.frontend.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("/newstype")
public class NewsTypeController extends BaseController{
	
	@Autowired
	private NewsDetailAppService newsDetailAppService;
	
	@RequestMapping("/index")
	public String toIndex(Model model
			,@RequestParam (value="typeid") String typeid
			,@RequestParam (value="typename") String typename
			,HttpServletRequest request) throws UnsupportedEncodingException{
		getAllNewsType(model);
		getHotAndPicNews(model);
		typename = new String(typename.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("类别ID为："+typeid+"类别名称为："+typename);
		PageData<NewsDetailDto> pageData = new PageData<NewsDetailDto>();
		pageData.setPageSize(8);
		pageData = newsDetailAppService.getNewsByTypeId(pageData,typeid);
		List<NewsDetailDto> typeNews = pageData.getRows();
		setPagination(model, pageData, request);
		model.addAttribute("typeNews", typeNews);
		model.addAttribute("typename", typename);
		return "/newstype/news_type";
	}
}
