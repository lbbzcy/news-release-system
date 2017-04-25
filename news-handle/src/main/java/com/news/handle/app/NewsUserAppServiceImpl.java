package com.news.handle.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.common.core.dto.PageData;
import com.news.common.project.app.NewsUserAppService;
import com.news.common.project.dto.NewsUserDto;
import com.news.handle.service.NewsUserService;

@Service("newsUserAppService")
public class NewsUserAppServiceImpl implements NewsUserAppService {

	@Autowired
	private NewsUserService newsUserService;
	@Override
	public List<NewsUserDto> getUserByName(String username) {
		return newsUserService.getUserByName(username);
	}

	@Override
	public List<NewsUserDto> getUserByMobile(String mobile) {
		return newsUserService.getUserByMobile(mobile);
	}

	@Override
	public List<NewsUserDto> getUserByEmail(String email) {
		return newsUserService.getUserByEmail(email);
	}

	@Override
	public int insertNewsUserDto(NewsUserDto newsUserDto) {
		return newsUserService.insertNewsUserDto(newsUserDto);
	}

	@Override
	public NewsUserDto getUserByAuth(String auth) {
		return newsUserService.getUserByAuth(auth);
	}

	@Override
	public PageData<NewsUserDto> findPageWithUser(PageData<NewsUserDto> pageData, NewsUserDto queryParam) {
		return newsUserService.findPageWithUser(pageData,queryParam);
	}

}
