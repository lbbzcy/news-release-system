package com.news.handle.service;

import com.news.common.project.dto.NewsAdminUserDto;

public interface NewsAdminUserService {
	public NewsAdminUserDto getUserByName(String username);
}
