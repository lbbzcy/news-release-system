package com.news.backend.common;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.news.common.core.dto.PageData;
import com.news.common.core.utils.SessionKey;
import com.news.common.core.utils.page.PaginationInfo;
import com.news.common.project.dto.NewsAdminUserDto;

public class BaseController {
	protected Logger _log = LoggerFactory.getLogger(getClass());
	
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
	/**
	 * 获取当前登录用户
	 * @param request
	 * @return
	 */
	protected NewsAdminUserDto getCurrentUser(HttpSession session){
		return (NewsAdminUserDto)session.getAttribute(SessionKey.LOGIN_ACCOUNT);
	}
	public static void invalidate(HttpServletRequest request){
		request.getSession().invalidate();
	}
}
