package com.news.frontend.common;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.common.core.dto.PageData;
import com.news.common.core.utils.GraphicsUtil;
import com.news.common.core.utils.SessionKey;
import com.news.common.core.utils.page.PaginationInfo;
import com.news.common.project.app.NewsDetailAppService;
import com.news.common.project.app.NewsTypeAppService;
import com.news.common.project.dto.NewsDetailDto;
import com.news.common.project.dto.NewsTypeDto;
import com.news.common.project.dto.NewsUserDto;
@Controller
public class BaseController {

	protected Logger _log = LoggerFactory.getLogger(getClass());

	@Autowired
	private NewsTypeAppService newsTypeAppService;
	@Autowired
	private NewsDetailAppService newsDetailAppService;

	/**
	 * 获取热门新闻和热门图片新闻
	 * 
	 * @param model
	 */
	protected void getHotAndPicNews(Model model) {
		PageData<NewsDetailDto> hotNewsList = new PageData<NewsDetailDto>();
		hotNewsList.setPageNumber(1);
		hotNewsList.setPageSize(3);
		hotNewsList = newsDetailAppService.getHotNews(hotNewsList);
		PageData<NewsDetailDto> picNewsList = new PageData<NewsDetailDto>();
		picNewsList.setPageNumber(1);
		picNewsList.setPageSize(1);
		picNewsList = newsDetailAppService.getPicNews(picNewsList);
		model.addAttribute("hotNewsList", hotNewsList.getRows());
		model.addAttribute("picNewsList", picNewsList.getRows());
	}

	/**
	 * 分页控件
	 * 
	 * @param model
	 * @param pageData
	 * @param request
	 */
	protected void setPagination(Model model, PageData<?> pageData, HttpServletRequest request) {

		Enumeration<?> enums = request.getParameterNames();
		StringBuilder sb = new StringBuilder();
		while (enums.hasMoreElements()) {
			String key = (String) enums.nextElement();
			if ("t".equals(key) || "menuclick".equals(key) || "pageNumber".equals(key) || "pageSize".equals(key)) {
				continue;
			}
			sb.append("&" + key + "=" + request.getParameter(key));
		}
		model.addAttribute("pagination", new PaginationInfo(pageData));
		try {
			model.addAttribute("paginationQueryParam", new String(sb.toString().getBytes(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			_log.error("", e);
		}
	}

	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping("getCode")
	public void getCode(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			// 表明生成的响应是图片
			response.setContentType("image/jpeg");

			Map<String, Object> map = new GraphicsUtil().getGraphics();
			System.out.println(map.get("rand"));
			request.getSession().setAttribute("rand", map.get("rand"));
			ImageIO.write((RenderedImage) map.get("image"), "JPEG", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void getAllNewsType(Model model) {
		List<NewsTypeDto> list = newsTypeAppService.listAllTypes();
		model.addAttribute("data", list);
	}

	protected NewsUserDto getCurrentUser(HttpSession session) {
		return (NewsUserDto) session.getAttribute(SessionKey.LOGIN_USER);
	}

	public static void invalidate(HttpServletRequest request) {
		request.getSession().invalidate();
	}
}
