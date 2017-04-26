package com.news.backend.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.news.backend.common.BaseController;
import com.news.backend.common.UploadToFtpService;
import com.news.common.core.dto.PageData;
import com.news.common.core.utils.JsonUtils;
import com.news.common.project.app.NewsBannerAppService;
import com.news.common.project.app.NewsDetailAppService;
import com.news.common.project.dto.NewsAdminUserDto;
import com.news.common.project.dto.NewsBannerDto;
import com.news.common.project.dto.NewsDetailDto;
@Controller
@RequestMapping("/banner/")
public class NewsBannerController extends BaseController {
	public static final String REQUEST_BASE_PATH = "/banner/";
	public static final String PAGE_BASE_PATH = "banner/";
	public static final String NEWS_BANNER_LIST_PATH = PAGE_BASE_PATH + "index";
	public static final String NEWS_BANNER_EDIT_PATH = PAGE_BASE_PATH + "edit";
	
	@Resource
	private NewsDetailAppService newsDetailAppService;
	@Resource
	private NewsBannerAppService newsBannerAppService;
	/** 
	 * 分页加载数据
	 * @param model
	 * @param pageData
	 * @param queryParam
	 * @param request
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model model,PageData<NewsBannerDto> pageData,NewsBannerDto queryParam,HttpServletRequest request){
		pageData = newsBannerAppService.findPageWithBanner(pageData, queryParam);
		model.addAttribute("pageData", pageData);
		// 分页显示
		setPagination(model, pageData, request);
		return NEWS_BANNER_LIST_PATH;
	}
	/**
	 * 跳转到新增/编辑页面
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="edit")
	public String showNewsBannerEdit(@RequestParam(value="id",required=false) String id,HttpServletRequest request,Model model,PageData<NewsDetailDto> pageData,NewsDetailDto queryParam){
		if(id != null && !"".equals(id)){
			NewsBannerDto entity = 	newsBannerAppService.findBannerById(id);
			model.addAttribute("entity",entity);
		}
		//获取新闻类别list和模板list
		pageData  = newsDetailAppService.findPageWithType(pageData, queryParam);
		model.addAttribute("pageData", pageData);
		// 分页显示
		setPagination(model, pageData, request);
		return NEWS_BANNER_EDIT_PATH;
	}
	/**
	 * 上传banner图片
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("upload")
	@ResponseBody
	public Map<String,String> uploadBanner(@RequestParam MultipartFile file,HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, String> result = UploadToFtpService.uploadPicture(file);
		System.out.println("返回结果:"+JsonUtils.objectToJson(result));
		return result;
	}
	@RequestMapping("insert")
	public String insert(@RequestParam(value="id",required=false) String id,
			@RequestParam(value="mimg") String imgsrc,
			@RequestParam(value="link") String link,
			@RequestParam(value="title") String title,
			@RequestParam(value="briefintroduction") String briefintroduction,
			HttpServletRequest request){
		//http://192.168.16.248/images/2017/04/09/d82b59e21183471b8c2ab0d077e3b2be.jpg
		imgsrc = imgsrc.substring(imgsrc.lastIndexOf("/images/")+7);
		System.out.println("图片地址为:"+imgsrc);
		System.out.println("banner链接地址为:"+link);
		String newsid = link.substring(link.lastIndexOf("=")+1);
		HttpSession session = request.getSession();
		NewsAdminUserDto adminuser = getCurrentUser(session);
		System.out.println("类别的ID为："+id);
		//判断为新增还是修改
		NewsBannerDto newsEntity = newsBannerAppService.findBannerById(id);
		if(null==newsEntity){
			//新增
			newsEntity = new NewsBannerDto();
			newsEntity.setId(newsEntity.getIdentity());
			newsEntity.setNewsid(newsid);
			newsEntity.setImgsrc(imgsrc);
			newsEntity.setLink(link);
			newsEntity.setTitle(title);
			newsEntity.setBriefintroduction(briefintroduction);
			newsEntity.setCreator(adminuser.getName());
			newsEntity.setUpdator(adminuser.getName());
			newsEntity.setCreatetime(new Date());
			newsEntity.setUpdatetime(new Date());
			newsEntity.setIsdel("0");
			newsBannerAppService.insertNewsBannerDto(newsEntity);
		}else{
			//修改
			newsEntity.setNewsid(newsid);
			newsEntity.setImgsrc(imgsrc);
			newsEntity.setLink(link);
			newsEntity.setTitle(title);
			newsEntity.setBriefintroduction(briefintroduction);
			newsEntity.setUpdator(adminuser.getName());
			newsEntity.setUpdatetime(new Date());
			newsBannerAppService.updateNewsBannerDto(newsEntity);
		}
		return redirectToList();
	}
	/**
	 * 检查新闻是否已经关联banner
	 * @param name
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/checkNewsBanner",method=RequestMethod.POST)
	@ResponseBody
	public String checkNewsBanner(@RequestParam String id,HttpServletRequest request){
		List<NewsBannerDto> entity = newsBannerAppService.findByNewsId(id);
		System.out.println("idwei:"+id);
		
		if(!entity.isEmpty()){
			System.out.println("1");
			return "failure";
		} 
		System.out.println("2");
		return "success";
	}
	/**
	 * 删除新闻banner
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam String id, HttpServletRequest request){
		try {
			int result = newsBannerAppService.deleteNewsBannerDto(id);
			if(result<1){
				return "failure";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String redirectToList() {
		return "redirect:"+REQUEST_BASE_PATH + "list.html";
	}
}
