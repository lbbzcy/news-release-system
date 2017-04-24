package com.news.common.project.app;

import java.util.List;

import com.news.common.project.dto.NewsUserDto;

public interface NewsUserAppService {
	public List<NewsUserDto> getUserByName(String username);
	
	public List<NewsUserDto> getUserByMobile(String mobile);
	
	public List<NewsUserDto> getUserByEmail(String email);
	
	public int insertNewsUserDto(NewsUserDto newsUserDto);
}
