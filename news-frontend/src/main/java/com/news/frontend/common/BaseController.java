package com.news.frontend.common;


import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.news.common.core.dto.PageData;
import com.news.common.core.utils.SessionKey;
import com.news.common.core.utils.page.PaginationInfo;
import com.news.common.project.app.NewsDetailAppService;
import com.news.common.project.app.NewsTypeAppService;
import com.news.common.project.dto.NewsDetailDto;
import com.news.common.project.dto.NewsTypeDto;
import com.news.common.project.dto.NewsUserDto;

public class BaseController {
	
	protected Logger _log = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	private NewsTypeAppService newsTypeAppService;
	@Autowired
	private NewsDetailAppService newsDetailAppService;
	
	/**
	 * 获取热门新闻和热门图片新闻
	 * @param model
	 */
	protected void getHotAndPicNews(Model model){
		PageData<NewsDetailDto> hotNewsList = new PageData<NewsDetailDto>();
		hotNewsList.setPageNumber(1);
		hotNewsList.setPageSize(3);
		hotNewsList = newsDetailAppService.getHotNews(hotNewsList);
		PageData<NewsDetailDto> picNewsList = new PageData<NewsDetailDto>();
		picNewsList.setPageNumber(1);
		picNewsList.setPageSize(1);
		picNewsList = newsDetailAppService.getPicNews(picNewsList);
		model.addAttribute("hotNewsList", hotNewsList.getRows());
		model.addAttribute("picNewsList", picNewsList.getRows());
	}
	
	/**
	 * 分页控件
	 * @param model
	 * @param pageData
	 * @param request
	 */
	protected void setPagination(Model model, PageData<?> pageData,
			HttpServletRequest request) {

		Enumeration<?> enums = request.getParameterNames();
		StringBuilder sb = new StringBuilder();
		while (enums.hasMoreElements()) {
			String key = (String) enums.nextElement();
			if("t".equals(key) || "menuclick".equals(key) 
					|| "pageNumber".equals(key) || "pageSize".equals(key)){
				continue;
			}
			sb.append("&" + key + "=" + request.getParameter(key));
		}
		model.addAttribute("pagination", new PaginationInfo(pageData));
		try {
			model.addAttribute("paginationQueryParam", new String(sb.toString().getBytes(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			_log.error("",e);
		}
	}
	protected void getAllNewsType(Model model) {
		List<NewsTypeDto> list = newsTypeAppService.listAllTypes();
		model.addAttribute("data", list);
	}
	
	protected NewsUserDto getCurrentUser(HttpSession session) {
		return (NewsUserDto)session.getAttribute(SessionKey.LOGIN_USER);
	}
	
	public static void invalidate(HttpServletRequest request){
		request.getSession().invalidate();
	}
}
