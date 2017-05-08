package com.news.common.project.app;

import java.util.List;

import com.news.common.core.dto.PageData;
import com.news.common.project.dto.NewsUserDto;

public interface NewsUserAppService {
	public List<NewsUserDto> getUserByName(String username);
	
	public List<NewsUserDto> getUserByMobile(String mobile);
	
	public List<NewsUserDto> getUserByEmail(String email);
	
	public int updateUser(NewsUserDto user);
	
	public int insertNewsUserDto(NewsUserDto newsUserDto);
	
	public NewsUserDto getUserById(String id);

	public PageData<NewsUserDto> findPageWithUser(PageData<NewsUserDto> pageData, NewsUserDto queryParam);

	public NewsUserDto getUserByAuth(String username, String password);

}
