package com.news.backend.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.news.backend.common.BaseController;
import com.news.common.core.utils.EncodeUtil;
import com.news.common.project.app.NewsAdminUserAppService;
import com.news.common.project.dto.NewsAdminUserDto;


@Controller
@RequestMapping("/admin")
public class NewsAdminUserController extends BaseController{
	
	public static final String LOGIN_PATH = "/login";
	@Autowired
	private NewsAdminUserAppService newsAdminUserAppService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String handleLogin(NewsAdminUserDto param,HttpServletRequest request){
		//获取用户名和密码
		String username = param.getName();
		String password = param.getPassword();
		//验证
		if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
			request.setAttribute("msg", "用户名或密码为空");
			return LOGIN_PATH;
		}
		NewsAdminUserDto user = newsAdminUserAppService.getUserByName(username);
		if(null==user){
			request.setAttribute("msg", "账号密码输入错误");
			return LOGIN_PATH;
		}
		
		String aclEncodePwd = user.getPassword();//密文
		String pwd = EncodeUtil.encode(password, user.getSalt());
		
		if(!aclEncodePwd.equals(pwd)){
			request.setAttribute("msg", "账号密码输入错误");
			return LOGIN_PATH;
		}
		//验证成功
		//addSessionUser(user, request);
		return "redirect:/index.html";
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		invalidate(request);
		return "redirect:/loginpage.html";
	}
}
