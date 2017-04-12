package com.news.backend.common;

import javax.servlet.http.HttpServletRequest;

public class CommonsMultipartResolver extends org.springframework.web.multipart.commons.CommonsMultipartResolver{

	@Override
	public boolean isMultipart(HttpServletRequest request) {
		String uri = request.getRequestURI();
		//过滤使用百度Ueditor的URI
		if (uri.indexOf("upload/config")>0){
			System.out.println("CommonsMultipartResolver放行");
			return false;
		}
		return super.isMultipart(request);
	}
	
}	
