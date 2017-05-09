package com.news.frontend.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.news.common.project.app.NewsCommentAppService;
import com.news.common.project.app.NewsDetailAppService;
import com.news.common.project.dto.NewsCommentDto;
import com.news.common.project.dto.NewsDetailDto;
import com.news.common.project.dto.NewsUserDto;
import com.news.frontend.common.BaseController;

@Controller
@RequestMapping("/comment")
public class NewsCommentController extends BaseController {
	
	@Autowired
	private NewsCommentAppService newsCommentAppService;
	@Autowired
	private NewsDetailAppService newsDetailAppService;
	/**
	 * 验证用户是否登录
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkIsUserLogin")
	@ResponseBody
	public String checkIsUserLogin(HttpServletRequest request){
		HttpSession session = request.getSession();
		NewsUserDto user = getCurrentUser(session);
		if(null==user){
			System.out.println("用户还没有登录");
			//还没有登录
			return "failure";
		}
		return "success";
	}
	/**
	 * 回复评论功能
	 * @param request
	 * @param recontent
	 * @param replyid
	 * @param newsid
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/replay",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String replay(HttpServletRequest request
			,@RequestParam(value="recontent") String recontent
			,@RequestParam(value="replayid",required=false) String replyid
			,@RequestParam(value="newsid") String newsid) throws UnsupportedEncodingException{
		HttpSession session = request.getSession();
		NewsUserDto user = getCurrentUser(session);
		String method = request.getMethod();
		System.out.println("Method："+method);
		if("get".equals(method)||"GET".equals(method)){
			recontent = new String(recontent.getBytes("ISO-8859-1"), "UTF-8");
		}
		System.out.println("评论的内容为："+recontent+"新闻ID为："+newsid);
		if(null == replyid || "".equals(replyid)){
			replyid = "0";
		}
		NewsCommentDto commentDto = new NewsCommentDto();
		commentDto.setId(commentDto.getIdentity());
		commentDto.setReplayid(replyid);
		commentDto.setUserid(user.getId());
		commentDto.setUsername(user.getUsername());
		commentDto.setIsdel("0");
		commentDto.setLikenum(0L);
		commentDto.setNewsid(newsid);
		commentDto.setNewstitle(newsDetailAppService.findNewsById(newsid).getTitle());
		commentDto.setContent(recontent);
		commentDto.setCreatetime(new Date());
		newsCommentAppService.insertComment(commentDto);
		//当前新闻评论数量增加
		NewsDetailDto newsEntity = newsDetailAppService.findNewsById(newsid);
		newsEntity.setCommentnum(newsEntity.getCommentnum()+1);
		newsDetailAppService.updateNewsDetailDto(newsEntity);
		return "success";
	}
}
