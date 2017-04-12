package com.news.backend.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.backend.common.BaseController;
import com.news.common.project.dto.NewsAdminUserDto;

@Controller
public class SecurityController extends BaseController{
	/**
	 * 跳转到用户登录页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String login(NewsAdminUserDto user,Model model, HttpServletRequest request) {
		// 如果登录成功(session未失效)，直接返回到主页面
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "redirect:/index.html";
		} else {
			_log.info("进入登录页面：login/login");
			return "login/login";
		}
	}
	/**
	 * 登出
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		NewsAdminUserDto currentUser = getCurrentUser(session);
		if(currentUser==null){
			return "redirect:/login.html";
		}
		//注销
		SecurityUtils.getSubject().logout();
		return "redirect:/login.html";
	}
}
