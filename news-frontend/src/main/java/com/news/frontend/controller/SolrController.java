package com.news.frontend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.news.common.core.dto.PageData;
import com.news.common.core.utils.SolrUtils;
import com.news.common.project.dto.NewsDetailDto;
import com.news.frontend.common.BaseController;

@Controller
@RequestMapping("/solr")
public class SolrController extends BaseController {
	@RequestMapping("/search")
	public String search(@RequestParam(value="queryParam") String queryParam 
			,HttpServletRequest request
			,Model model
			,PageData<NewsDetailDto> pageData) throws Exception {
		getAllNewsType(model);
		System.out.println("queryparam:"+queryParam);
		queryParam = new String(queryParam.getBytes("ISO-8859-1"), "UTF-8");
		if(null != queryParam && !"".equals(queryParam)){
			pageData.setPageSize(8);
			List<NewsDetailDto> queryList = SolrUtils.querySolr(queryParam);
			pageData.setRows(queryList);
			model.addAttribute("pageData", pageData);
			setPagination(model, pageData, request);
		}
		return "/solr/result";
	}
}
