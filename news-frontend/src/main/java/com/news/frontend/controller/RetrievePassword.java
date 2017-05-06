package com.news.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.news.frontend.common.BaseController;
@Controller
@RequestMapping("/retrievepwd")
public class RetrievePassword extends BaseController {
	@RequestMapping("/step1")
	public String step1(){
		return "/retrievepwd/step1";
	}
	@RequestMapping("/step2")
	public String step2(){
		return "/retrievepwd/step2";
	}
	@RequestMapping("/step3")
	public String step3(){
		return "/retrievepwd/step3";
	}
	@RequestMapping("/step4")
	public String step4(){
		return "/retrievepwd/step4";
	}
}
