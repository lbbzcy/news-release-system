package com.news.frontend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.news.common.project.app.NewsCollectAppService;
import com.news.common.project.dto.NewsCollectDto;
import com.news.common.project.dto.NewsUserDto;
import com.news.frontend.common.BaseController;
@Controller
@RequestMapping("/personal")
public class PersonalController extends BaseController {
	@Autowired
	private NewsCollectAppService newsCollectAppService;
	@RequestMapping("/index")
	public String index(Model model){
		getAllNewsType(model);
		return "/personal/index";
	}
	@RequestMapping(value="/collect",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String collect(HttpServletRequest request,
			@RequestParam(value="newsid") String newsid){
		NewsUserDto user = getCurrentUser(request.getSession());
		String userid = user.getId();
		System.out.println("新闻ID为："+newsid);
		NewsCollectDto collect = new NewsCollectDto();
		collect.setId(collect.getIdentity());
		collect.setNewsId(newsid);
		collect.setUserId(userid);
		int result = newsCollectAppService.insert(collect);
		if(result>0){
			return "success";
		}else{
			return "failure";
		}
		
	}
}
