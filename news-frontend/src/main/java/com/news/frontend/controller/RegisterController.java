package com.news.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@RequestMapping("/index")
	public String toIndex(){
		return "/register/register";
	}
}
