package com.news.backend.controller;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.news.backend.common.BaseController;
import com.news.common.core.dto.PageData;
import com.news.common.project.app.NewsTypeAppService;
import com.news.common.project.dto.NewsAdminUserDto;
import com.news.common.project.dto.NewsTypeDto;

@Controller
@RequestMapping("/news_type")
public class NewsTypeController extends BaseController{
	
	public static final String REQUEST_BASE_PATH = "/news_type/";
	public static final String PAGE_BASE_PATH = "news_type/";
	public static final String NEWS_TYPE_LIST_PATH = PAGE_BASE_PATH + "index";
	public static final String NEWS_TYPE_EDIT_PATH = PAGE_BASE_PATH + "edit";
	@Resource
	private NewsTypeAppService newsTypeAppService;
	
	
	/**
	 * 分页查询新闻类别
	 * @param model
	 * @param pageData
	 * @param queryParam
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model,PageData<NewsTypeDto> pageData,NewsTypeDto queryParam,HttpServletRequest request){
		pageData = newsTypeAppService.findPageWithType(pageData, queryParam);
		model.addAttribute("pageData", pageData);
		// 分页显示
		setPagination(model, pageData, request);
		return NEWS_TYPE_LIST_PATH;
	}
	/**
	 * 跳转到新增/编辑页面
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit")
	public String showNewsTypeEdit(@RequestParam(value="id",required=false) String id,HttpServletRequest request,Model model){
		NewsTypeDto entity = newsTypeAppService.findNewsById(id);
		model.addAttribute("entity", entity);
		return NEWS_TYPE_EDIT_PATH;
	}
	/**
	 * 新增/修改新闻类别
	 * @param id
	 * @param name
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add")
	public String addNewsType(@RequestParam(value="id",required=false) String id,@RequestParam String name,HttpServletRequest request){
		HttpSession session = request.getSession();
		NewsAdminUserDto adminuser = getCurrentUser(session);
		System.out.println("类别的ID为："+id);
		//判断为新增还是修改
		NewsTypeDto newsEntity = newsTypeAppService.findNewsById(id);
		if(null==newsEntity){
			//新增
			newsEntity = new NewsTypeDto();
			newsEntity.setId(newsEntity.getIdentity());
			newsEntity.setName(name);
			newsEntity.setCreator(adminuser.getName());
			newsEntity.setUpdator(adminuser.getName());
			newsEntity.setCreatetime(new Date());
			newsEntity.setUpdatetime(new Date());
			newsEntity.setIsdel("0");
			newsTypeAppService.insertNewsTypeDto(newsEntity);
		}else{
			//修改
			newsEntity.setName(name);
			newsEntity.setUpdator(adminuser.getName());
			newsEntity.setUpdatetime(new Date());
			newsTypeAppService.updateNewsTypeDto(newsEntity);
		}
		return redirectToList();
	}
	/**
	 * 删除新闻类别
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam String id, HttpServletRequest request){
		int result = newsTypeAppService.deleteNewsTypeDto(id);
		if(result<1){
			return "failure";
		}
		return "success";
	}
	/**
	 * 检查新闻类别是否存在
	 * @param name
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/checkNewsType",method=RequestMethod.POST)
	@ResponseBody
	public String checkNewsType(@RequestParam String name,HttpServletRequest request){
		List<NewsTypeDto> entity = newsTypeAppService.findByName(name);
		if(entity.size()>0){
			System.out.println("不为空");
			return "failure";
		}
		return "success";
	}
	public String redirectToList() {
		return "redirect:"+REQUEST_BASE_PATH + "list.html";
	}
}
