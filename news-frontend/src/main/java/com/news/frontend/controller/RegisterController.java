package com.news.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.news.frontend.common.BaseController;

@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController{
	@RequestMapping("/index")
	public String toIndex(Model model){
		getAllNewsType(model);
		return "/register/register";
	}
	@RequestMapping(value="/checkUserName",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String checkUserName(@RequestParam(value="username") String username){
		System.out.println("注册的用户名为:"+username);
		return "false";
	}
}
