package com.news.backend.controller;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidu.ueditor.ActionEnter;
import com.news.backend.common.BaseController;
import com.news.common.project.dto.NewsAdminUserDto;

/**
 * 首页跳转控制
 * 
 * @author zcy
 *
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
	/**
	 * 进入后台首页
	 * 
	 * @return
	 */
	@RequestMapping("index")
	public String showIndex(HttpServletRequest request) {
		HttpSession session = request.getSession();
		NewsAdminUserDto user = getCurrentUser(session);
		request.setAttribute("user", user);
		return "/index";
	}

	@RequestMapping("index_v1")
	public String showIndexV1(HttpServletRequest request) {
		return "/index_v1";
	}

	/**
	 * 其他页面展示
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "loginpage")
	public String showPage() {
		return "/login";
	}

	@RequestMapping("upload/config")
	public void upload(HttpServletRequest request, HttpServletResponse response)  
            throws Exception {  
		try {
			System.out.println("zhuchunyu");
			response.setContentType("application/json");
			request.setCharacterEncoding("utf-8");
			response.setHeader("Content-Type", "text/html");
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			System.out.println("123"+request.getParameter("action"));
			System.out.println("rootPath:"+rootPath);
			String exec = new ActionEnter(request, rootPath).exec();
			PrintWriter writer = response.getWriter();
			writer.write(exec);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
