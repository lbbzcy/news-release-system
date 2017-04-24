package com.news.frontend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.news.common.project.app.NewsUserAppService;
import com.news.common.project.dto.NewsUserDto;
import com.news.frontend.common.BaseController;

@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController{
	@Autowired
	private NewsUserAppService newsUserAppService;
	/**
	 * 跳转到注册页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String toIndex(Model model){
		getAllNewsType(model);
		return "/register/register";
	}
	/**
	 * 校验用户名是否可用
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/checkUserName",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String checkUserName(@RequestParam(value="username") String username){
		List<NewsUserDto> list = newsUserAppService.getUserByName(username);
		if(list.size()>0){
			return "false";
		} else{
			return "true";
		}
	}
	/**
	 * 校验手机号码是否可以使用
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value="/checkMobile",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String checkMobile(@RequestParam(value="mobile") String mobile){
		List<NewsUserDto> list = newsUserAppService.getUserByMobile(mobile);
		if(list.size()>0){
			return "false";
		} else{
			return "true";
		}
	}
	/**
	 * 校验邮箱是否已经绑定
	 * @param email
	 * @return
	 */
	@RequestMapping(value="/checkEmail",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String checkEmail(@RequestParam(value="email") String email){
		List<NewsUserDto> list = newsUserAppService.getUserByEmail(email);
		if(list.size()>0){
			return "false";
		} else{
			return "true";
		}
	}
	/**
	 * 用户注册
	 * @param request
	 * @param paramter
	 * @return
	 */
	@RequestMapping("register")
	public String register(HttpServletRequest request,NewsUserDto paramter){
		paramter.setId(paramter.getIdentity());
		paramter.setIsdel("0");
		int result = newsUserAppService.insertNewsUserDto(paramter);
		if(result>0){
			return "redirect:/main/index.html";
		}else{
			request.setAttribute("register_error", "注册失败,请稍后再试!");
			return "redirect:/register/register.html";
		}
	}
}
