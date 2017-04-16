package com.news.frontend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.ext.servlet.FreemarkerServlet;

public class ShieFreemarkerServlet extends FreemarkerServlet{
	
	private static final long serialVersionUID = 3220444120756663412L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决首页以及错误页面获取不到contextPath问题
		if(request.getParameter("contextPath")==null){
			request.setAttribute("contextPath", request.getContextPath());
		}
		super.doGet(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//解决首页以及错误页面获取不到contextPath问题
		if(request.getParameter("contextPath")==null){
			request.setAttribute("contextPath", request.getContextPath());
		}
		super.doPost(request, response);
	}
}
