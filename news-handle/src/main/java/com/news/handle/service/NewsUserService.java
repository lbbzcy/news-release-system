package com.news.handle.service;

import java.util.List;

import com.news.common.core.dto.PageData;
import com.news.common.project.dto.NewsUserDto;

public interface NewsUserService {
	
	public List<NewsUserDto> getUserByName(String username);
	
	public List<NewsUserDto> getUserByMobile(String mobile);
	
	public List<NewsUserDto> getUserByEmail(String email);
	
	public int insertNewsUserDto(NewsUserDto newsUserDto);
	
	public PageData<NewsUserDto> findPageWithUser(PageData<NewsUserDto> pageData, NewsUserDto queryParam);

	public NewsUserDto getUserByAuth(String auth, String password);
}
