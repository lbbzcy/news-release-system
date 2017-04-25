package com.news.frontend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.news.common.project.app.NewsUserAppService;
import com.news.common.project.dto.NewsUserDto;
import com.news.frontend.common.BaseController;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	@Autowired
	private NewsUserAppService newsUserAppService;
	@RequestMapping(value="/handleLogin",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String handleLogin(@RequestParam(value="username") String username,
			@RequestParam(value="password") String password,
			HttpServletRequest request,
			HttpSession session){
		System.out.println("authc:"+username);
		NewsUserDto user = newsUserAppService.getUserByAuth(username);
		if(null != user){
			writeUserToSession(user, request);
			return "success";
		}else{
			return "error";
		}
	}
}
