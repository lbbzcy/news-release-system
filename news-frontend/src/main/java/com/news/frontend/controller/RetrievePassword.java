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

import com.news.common.core.utils.MailUtils;
import com.news.common.project.app.NewsUserAppService;
import com.news.common.project.dto.NewsUserDto;
import com.news.frontend.common.BaseController;
@Controller
@RequestMapping("/retrievepwd")
public class RetrievePassword extends BaseController {
	@Autowired
	private NewsUserAppService newsUserAppService;
	/**
	 * 跳转到找回密码页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/step1")
	public String step1(Model model){
		getAllNewsType(model);
		return "/retrievepwd/step1";
	}
	/**
	 * 发送找回密码邮件
	 * @param model
	 * @param request
	 * @param username
	 * @return
	 */
	@RequestMapping("/step2")
	public String step2(Model model
			,HttpServletRequest request
			,@RequestParam(value="username") String username){
		getAllNewsType(model);
		try {
			List<NewsUserDto> list = newsUserAppService.getUserByName(username);
			if(list==null || list.size()<=0){
				list = newsUserAppService.getUserByMobile(username);
			}else{
				list = newsUserAppService.getUserByEmail(username);
			}
			model.addAttribute("user", list.get(0));
			MailUtils.sendMail(list.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/retrievepwd/step2";
	}
	/**
	 * 邮件点击跳转页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/step3")
	public String step3(Model model,@RequestParam(value="id") String id){
		getAllNewsType(model);
		NewsUserDto  dto = newsUserAppService.getUserById(id);
		if(null != dto){
			model.addAttribute("user", dto);
		}
		return "/retrievepwd/step3";
	}
	@RequestMapping("/step4")
	public String step4(Model model,
			@RequestParam(value="password") String password,
			@RequestParam(value="userid") String userid){
		getAllNewsType(model);
		System.out.println("密码为："+password);
		NewsUserDto  dto = newsUserAppService.getUserById(userid);
		if(null != dto){
			dto.setPassword(password);
			int result = newsUserAppService.updateUser(dto);
			if(result>0){
				return "/retrievepwd/step4";
			}else{
				model.addAttribute("user", dto);
				model.addAttribute("errormsg", "找回密码失败，请稍后再试！");
				return "/retrievepwd/step3";
			}
		}
		return null;
	}
	/**
	 * 验证验证码的正确性
	 * @param request
	 * @param imgcode
	 * @return
	 */
	@RequestMapping("/validCode")
	@ResponseBody
	public String validCode(HttpServletRequest request,
			@RequestParam(value="imgcode") String imgcode){
		String rand = (String)request.getSession().getAttribute("rand");
		imgcode = imgcode.toUpperCase();
		if(rand.equals(imgcode)){
			return "success";
		}else{
			return "failure";
		}
		
	}
	/**
	 * 校验用户名是否可用
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/checkUser",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String checkUserName(@RequestParam(value="username") String username){
		List<NewsUserDto> list = newsUserAppService.getUserByName(username);
		if(list==null || list.size()<=0){
			list = newsUserAppService.getUserByMobile(username);
		}else{
			list = newsUserAppService.getUserByEmail(username);
		}
		if(list.size()>0){
			return "false";
		} else{
			return "true";
		}
	}
}
