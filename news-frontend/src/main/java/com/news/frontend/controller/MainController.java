package com.news.frontend.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.common.core.dto.PageData;
import com.news.common.project.app.NewsBannerAppService;
import com.news.common.project.app.NewsDetailAppService;
import com.news.common.project.dto.NewsBannerDto;
import com.news.common.project.dto.NewsDetailDto;
import com.news.frontend.common.BaseController;


@Controller
@RequestMapping("/main")
public class MainController extends BaseController{
	
	@Autowired
	private NewsBannerAppService newsBannerAppService;
	@Autowired
	private NewsDetailAppService newsDetailAppService;
	/**
	 * 首页显示
	 * @param model
	 * @param request
	 * @param newsDetailPageData
	 * @return
	 */
	@RequestMapping("/index")
	public String toIndex(Model model,
			HttpServletRequest request,
			PageData<NewsDetailDto> newsDetailPageData){
		getAllNewsType(model);
		//获取所有的banner
		PageData<NewsBannerDto> pageData = new PageData<NewsBannerDto>();
		NewsBannerDto newsBannerDto = new NewsBannerDto();
		pageData = newsBannerAppService.findPageWithBanner(pageData, newsBannerDto);
		List<NewsBannerDto> bannerList = pageData.getRows();
		model.addAttribute("bannerList", bannerList);
		//获取新闻
		newsDetailPageData.setPageSize(4);
		newsDetailPageData = newsDetailAppService.findPageWithType(newsDetailPageData, new NewsDetailDto());
		setPagination(model, newsDetailPageData, request);
		List<NewsDetailDto> newsDetailList = newsDetailPageData.getRows();
		model.addAttribute("newsDetailList", newsDetailList);
		//获取热门和图片新闻
		getHotAndPicNews(model);
		return "/main/index";
	}
	/**
	 * 所有新闻显示
	 * @param model
	 * @param request
	 * @param newsDetailPageData
	 * @return
	 */
	@RequestMapping("/main_news")
	public String toMainNews(Model model,
			HttpServletRequest request,
			PageData<NewsDetailDto> newsDetailPageData){
		getAllNewsType(model);
		getHotAndPicNews(model);
		newsDetailPageData.setPageSize(8);
		newsDetailPageData = newsDetailAppService.findPageWithType(newsDetailPageData, new NewsDetailDto());
		List<NewsDetailDto> newsDetailList = newsDetailPageData.getRows();
		setPagination(model, newsDetailPageData, request);
		model.addAttribute("newsDetailList", newsDetailList);
		return "/main/main_news";
	}
}
