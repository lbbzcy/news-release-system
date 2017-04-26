package com.news.frontend.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.common.core.dto.PageData;
import com.news.common.project.app.NewsBannerAppService;
import com.news.common.project.dto.NewsBannerDto;
import com.news.frontend.common.BaseController;


@Controller
@RequestMapping("/main")
public class MainController extends BaseController{
	
	@Autowired
	private NewsBannerAppService newsBannerAppService;
	@RequestMapping("/index")
	public String toIndex(Model model,HttpServletRequest request){
		getAllNewsType(model);
		//获取所有的banner
		PageData<NewsBannerDto> pageData = new PageData<NewsBannerDto>();
		NewsBannerDto newsBannerDto = new NewsBannerDto();
		pageData = newsBannerAppService.findPageWithBanner(pageData, newsBannerDto);
		List<NewsBannerDto> bannerList = pageData.getRows();
		model.addAttribute("bannerList", bannerList);
		return "/main/index";
	}
	@RequestMapping("/main_news")
	public String toMainNews(Model model){
		getAllNewsType(model);
		return "/main/main_news";
	}
}
