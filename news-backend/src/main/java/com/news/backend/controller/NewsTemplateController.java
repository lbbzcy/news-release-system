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
import com.news.common.project.app.NewsTemplateAppService;
import com.news.common.project.dto.NewsAdminUserDto;
import com.news.common.project.dto.NewsTemplateDto;

@Controller
@RequestMapping("/template")
public class NewsTemplateController extends BaseController{
	
	public static final String REQUEST_BASE_PATH = "/template/";
	public static final String PAGE_BASE_PATH = "template/";
	public static final String NEWS_TEMPLATE_LIST_PATH = PAGE_BASE_PATH + "index";
	public static final String NEWS_TEMPLATE_EDIT_PATH = PAGE_BASE_PATH + "edit";
	
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
	public String list(Model model,PageData<NewsTemplateDto> pageData,NewsTemplateDto queryParam,HttpServletRequest request){
		pageData = newsTemplateAppService.findPageWithType(pageData, queryParam);
		model.addAttribute("pageData", pageData);
		// 分页显示
		setPagination(model, pageData, request);
		return NEWS_TEMPLATE_LIST_PATH;
	}
	/**
	 * 跳转到新增/编辑页面
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public String toNewsTemplate(@RequestParam(value="id",required=false) String id,HttpServletRequest request, Model model){
		NewsTemplateDto entity = newsTemplateAppService.findById(id);
		model.addAttribute("entity", entity);
		return NEWS_TEMPLATE_EDIT_PATH;
	}
	/**
	 * 检查模板是否存在
	 * @param name
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/checkTemplate",method=RequestMethod.POST)
	@ResponseBody
	public String checkTemplate(@RequestParam String name,HttpServletRequest request){
		System.out.println("模板名称为："+name);
		List<NewsTemplateDto> entity = newsTemplateAppService.findByName(name);
		if(!entity.isEmpty()){
			return "failure";
		}
		return "success";
	}
	/**
	 * 新增/修改模板
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/add")
	public String addTemplate(@RequestParam(value="id",required=false) String id,@RequestParam String name,HttpServletRequest request){
		HttpSession session = request.getSession();
		NewsAdminUserDto adminUser = getCurrentUser(session);
		NewsTemplateDto entity = newsTemplateAppService.findById(id);
		if(null == entity){
			//新增
			entity = new NewsTemplateDto();
			entity.setId(entity.getIdentity());
			entity.setName(name);
			entity.setCreator(adminUser.getName());
			entity.setUpdator(adminUser.getName());
			newsTemplateAppService.addTemplate(entity);
		}else{
			//修改
			entity.setName(name);
			entity.setUpdator(adminUser.getName());
			entity.setUpdatetime(new Date());
			newsTemplateAppService.updateTemplate(entity);
		}
		return redirectToList();
	}
	/**
	 * 删除template
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam String id,HttpServletRequest request){
		int result = newsTemplateAppService.deleteTemplate(id);
		if(result<1){
			return "failure";
		}
		return "success";
	}
	public String redirectToList() {
		return "redirect:"+REQUEST_BASE_PATH + "list.html";
	}
}
