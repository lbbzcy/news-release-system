package com.news.frontend.common;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.news.common.core.utils.SessionKey;
import com.news.common.project.app.NewsTypeAppService;
import com.news.common.project.dto.NewsTypeDto;
import com.news.common.project.dto.NewsUserDto;

public class BaseController {
	
	@Autowired 
	private NewsTypeAppService newsTypeAppService;
	
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
