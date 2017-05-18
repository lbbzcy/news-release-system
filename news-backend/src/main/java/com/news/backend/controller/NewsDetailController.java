package com.news.backend.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.news.backend.common.BaseController;
import com.news.backend.util.exl.DateUtil;
import com.news.common.core.constant.EYesNo;
import com.news.common.core.dto.PageData;
import com.news.common.core.dto.QueryDateDto;
import com.news.common.project.app.NewsDetailAppService;
import com.news.common.project.app.NewsTemplateAppService;
import com.news.common.project.app.NewsTypeAppService;
import com.news.common.project.dto.NewsAdminUserDto;
import com.news.common.project.dto.NewsDetailDto;
import com.news.common.project.dto.NewsTemplateDto;
import com.news.common.project.dto.NewsTypeDto;

@Controller
@RequestMapping("/news_edit")
public class NewsDetailController extends BaseController{
	public static final String REQUEST_BASE_PATH = "/news_edit/";
	public static final String PAGE_BASE_PATH = "news_edit/";
	public static final String NEWS_DETAIL_LIST_PATH = PAGE_BASE_PATH + "index";
	public static final String NEWS_DETAIL_EDIT_PATH = PAGE_BASE_PATH + "edit";
	@Resource
	private NewsDetailAppService newsDetailAppService;
	@Resource
	private NewsTypeAppService newsTypeAppService;
	@Resource
	private NewsTemplateAppService newsTemplateAppService;
	/** 
	 * 分页加载数据
	 * @param model
	 * @param pageData
	 * @param queryParam
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model model,PageData<NewsDetailDto> pageData,NewsDetailDto queryParam,HttpServletRequest request){
		String queryDateType = request.getParameter("queryDateType");
		if(StringUtils.isNotBlank(queryDateType)){
			QueryDateDto queryDateDto = DateUtil.getQueryDate(queryDateType);
			queryParam.setStartTime(queryDateDto.getStartTime());
			queryParam.setEndTime(queryDateDto.getEndTime());
		}
		pageData = newsDetailAppService.findPageWithType(pageData, queryParam);
		model.addAttribute("pageData", pageData);
		// 分页显示
		setPagination(model, pageData, request);
		model.addAttribute("isHotList", EYesNo.values());
		return NEWS_DETAIL_LIST_PATH;
	}
	public String redirectToList() {
		return "redirect:"+REQUEST_BASE_PATH + "list.html";
	}
	/**
	 * 跳转到新增/编辑页面
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit")
	public String showNewsDetailEdit(@RequestParam(value="id",required=false) String id,HttpServletRequest request,Model model){
		NewsDetailDto entity = newsDetailAppService.findNewsById(id);
		model.addAttribute("entity", entity);
		List<NewsTypeDto> newsTypeList = newsTypeAppService.listAllTypes();
		List<NewsTemplateDto> newsTemplateList = newsTemplateAppService.listAllTemplate();
		model.addAttribute("newsTypeList", newsTypeList);
		model.addAttribute("newsTemplateList", newsTemplateList);
		model.addAttribute("isHotList", EYesNo.values());
		//获取新闻类别list和模板list
		return NEWS_DETAIL_EDIT_PATH;
	}
	/**
	 * 新增/修改新闻
	 * @param id
	 * @param request
	 * @param newsDetailDto
	 * @return
	 */
	@RequestMapping(value="/add")
	public String addNewsDetail(@RequestParam(value="id",required=false) String id,@RequestParam(value="mimg",required=false) String mediasrc,HttpServletRequest request,NewsDetailDto newsDetailDto){
		HttpSession session = request.getSession();
		NewsAdminUserDto adminuser = getCurrentUser(session);
		mediasrc = mediasrc.substring(mediasrc.lastIndexOf("/images/")+7);
		//判断为新增还是修改
		NewsDetailDto newsEntity = newsDetailAppService.findNewsById(id);
		if(null==newsEntity){
			//新增
			newsDetailDto.setId(newsDetailDto.getIdentity());
			newsDetailDto.setCommentnum(0L);
			newsDetailDto.setViewnum(0L);
			newsDetailDto.setMediasrc(mediasrc);
			newsDetailDto.setCreatetime(new Date());
			newsDetailDto.setUpdatetime(new Date());
			newsDetailDto.setCreator(adminuser.getName());
			newsDetailDto.setUpdator(adminuser.getName());
			newsDetailDto.setIsdel("0");
			newsDetailAppService.insertNewsDetailDto(newsDetailDto);
		}else{
			//修改
			newsDetailDto.setUpdatetime(new Date());
			newsDetailDto.setMediasrc(mediasrc);
			newsDetailDto.setUpdator(adminuser.getName());
			newsDetailAppService.updateNewsDetailDto(newsDetailDto);
		}
		return redirectToList();
	}
	/**
	 * 删除新闻
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam String id, HttpServletRequest request){
		int result = newsDetailAppService.deleteNewsDetailDto(id);
		if(result<1){
			return "failure";
		}
		return "success";
	}
}
