package com.news.frontend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.news.common.core.utils.SessionKey;
import com.news.common.project.app.NewsCollectAppService;
import com.news.common.project.app.NewsDetailAppService;
import com.news.common.project.app.NewsUserAppService;
import com.news.common.project.dto.NewsCollectDto;
import com.news.common.project.dto.NewsDetailDto;
import com.news.common.project.dto.NewsUserDto;
import com.news.frontend.common.BaseController;
@Controller
@RequestMapping("/personal")
public class PersonalController extends BaseController {
	@Autowired
	private NewsCollectAppService newsCollectAppService;
	@Autowired
	private NewsDetailAppService newsDetailAppService;
	@Autowired
	private NewsUserAppService newsUserAppService;
	@RequestMapping("/index")
	public String index(Model model,HttpServletRequest request){
		getAllNewsType(model);
		//查詢用戶收藏的新聞
		NewsUserDto user = getCurrentUser(request.getSession());
		List<NewsDetailDto> collectList = newsDetailAppService.getAllCollectNewsByUserId(user.getId());
		model.addAttribute("collectList", collectList);	
		return "/personal/index";
	}
	/**
	 * 新聞收藏功能
	 * @param request
	 * @param newsid
	 * @return
	 */
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
	@RequestMapping(value="/cancelCollect",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String cancelCollect(HttpServletRequest request,
			@RequestParam(value="newsid") String newsid){
		NewsUserDto user = getCurrentUser(request.getSession());
		String userid = user.getId();
		NewsCollectDto collect = new NewsCollectDto();
		collect.setId(collect.getIdentity());
		collect.setNewsId(newsid);
		collect.setUserId(userid);
		int result = newsCollectAppService.delete(collect);
		if(result>0){
			return "success";
		}else{
			return "failure";
		}
	}
	/**
	 * 上传头像
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="upload",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> upload(@RequestParam String image,HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		NewsUserDto user = getCurrentUser(request.getSession());
		System.out.println(image);
		Map<String,String> result = new HashMap<>();
		result.put("file", image);
		user.setHeader(image);
		int res = newsUserAppService.updateUser(user);
		if(res>0){
			result.put("result", "ok");
			session.setAttribute(SessionKey.LOGIN_USER, user);
		}else{
			result.put("result", "error");
		}
		
		return result;
	}
}
