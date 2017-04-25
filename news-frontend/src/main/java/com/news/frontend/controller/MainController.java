package com.news.frontend.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.common.project.dto.NewsUserDto;
import com.news.frontend.common.BaseController;


@Controller
@RequestMapping("/main")
public class MainController extends BaseController{
	
	@RequestMapping("/index")
	public String toIndex(Model model,HttpServletRequest request,HttpSession session){
		getAllNewsType(model);
		NewsUserDto user = getCurrentUser(session);
		request.setAttribute("user", user);
		return "/main/index";
	}
	@RequestMapping("/main_news")
	public String toMainNews(Model model){
		getAllNewsType(model);
		return "/main/main_news";
	}
}
