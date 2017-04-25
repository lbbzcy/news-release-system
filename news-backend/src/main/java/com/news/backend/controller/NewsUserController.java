package com.news.backend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.backend.common.BaseController;
import com.news.common.core.dto.PageData;
import com.news.common.project.app.NewsUserAppService;
import com.news.common.project.dto.NewsUserDto;

@Controller
@RequestMapping("/user")
public class NewsUserController extends BaseController{
	@Autowired
	private NewsUserAppService newsUserAppService;
	@RequestMapping("/list")
	public String list(Model model,PageData<NewsUserDto> pageData,NewsUserDto queryParam,HttpServletRequest request){
		pageData = newsUserAppService.findPageWithUser(pageData, queryParam);
		model.addAttribute("pageData", pageData);
		// 分页显示
		setPagination(model, pageData, request);
		return "/user/index";
	}
}
