package com.news.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.frontend.common.BaseController;

@Controller
@RequestMapping("/register")
public class RegisterController extends BaseController{
	@RequestMapping("/index")
	public String toIndex(Model model){
		getAllNewsType(model);
		return "/register/register";
	}
}
