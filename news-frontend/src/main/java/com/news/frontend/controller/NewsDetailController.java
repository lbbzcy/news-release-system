package com.news.frontend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.news.common.core.dto.PageData;
import com.news.common.project.app.NewsCollectAppService;
import com.news.common.project.app.NewsCommentAppService;
import com.news.common.project.app.NewsDetailAppService;
import com.news.common.project.dto.NewsCollectDto;
import com.news.common.project.dto.NewsCommentDto;
import com.news.common.project.dto.NewsDetailDto;
import com.news.common.project.dto.NewsUserDto;
import com.news.frontend.common.BaseController;

@Controller
@RequestMapping("/newsdetail")
public class NewsDetailController extends BaseController{
	@Autowired
	private NewsDetailAppService newsDetailAppService;
	@Autowired
	private NewsCommentAppService newsCommentAppService;
	@Autowired
	private NewsCollectAppService newsCollectAppService;
	/**
	 * 新闻详情页面
	 * @param model
	 * @param newsid
	 * @param request
	 * @param commentPage
	 * @return
	 */
	@RequestMapping("/index")
	public String toNewsDetail(Model model,
			@RequestParam(value="newsid") String newsid,
			HttpServletRequest request,
			PageData<NewsCommentDto> commentPage){
		getAllNewsType(model);
		getHotAndPicNews(model);
		//获取新闻详情
		NewsDetailDto newsDetailDto = newsDetailAppService.findNewsById(newsid);
		model.addAttribute("newsItem",newsDetailDto);
		//判断新闻是否已经收藏
		NewsUserDto dto = getCurrentUser(request.getSession());
		if(dto != null){
			String userid = dto.getId();
			NewsCollectDto collect = new NewsCollectDto();
			collect.setUserId(userid);
			collect.setNewsId(newsid);
			collect = newsCollectAppService.select(collect);
			if(collect!=null){
				model.addAttribute("isCollect", true);
			}
		}
		//获取新闻相关评论
		commentPage.setPageSize(5);
		commentPage = newsCommentAppService.findPage(commentPage, newsDetailDto);
		setPagination(model, commentPage, request);
		List<NewsCommentDto> commentList = commentPage.getRows();
		model.addAttribute("commentList", commentList);
		model.addAttribute("totalComment", commentPage.getTotal());
		//浏览数量增加
		newsDetailDto.setViewnum(newsDetailDto.getViewnum()+1);
		newsDetailAppService.updateNewsDetailDto(newsDetailDto);
		//获取相关新闻
		PageData<NewsDetailDto> pageData = new PageData<NewsDetailDto>();
		pageData.setPageNumber(1);
		pageData.setPageSize(4);
		pageData = newsDetailAppService.findRelatedWithType(pageData, newsDetailDto);
		List<NewsDetailDto>  relatedNews = pageData.getRows();
		model.addAttribute("relatedNews", relatedNews);
		return "/newsdetail/index";
	}
}
