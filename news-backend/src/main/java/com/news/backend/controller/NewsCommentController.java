package com.news.backend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.backend.common.BaseController;
import com.news.common.core.dto.PageData;
import com.news.common.project.app.NewsCommentAppService;
import com.news.common.project.dto.NewsCommentDto;

@Controller
@RequestMapping("/comment")
public class NewsCommentController extends BaseController {
	
	public static final String PAGE_BASE_PATH = "comment/";
	public static final String NEWS_DETAIL_LIST_PATH = PAGE_BASE_PATH + "index";
	public static final String NEWS_DETAIL_EDIT_PATH = PAGE_BASE_PATH + "edit";
	@Autowired
	private NewsCommentAppService newsCommentAppService;
	
	@RequestMapping("/list")
	public String list(Model model,PageData<NewsCommentDto> pageData,NewsCommentDto queryParam,HttpServletRequest request){
		pageData = newsCommentAppService.findCommentPage(pageData, queryParam);
		model.addAttribute("pageData", pageData);
		// 分页显示
		setPagination(model, pageData, request);
		return NEWS_DETAIL_LIST_PATH;
	}
}
