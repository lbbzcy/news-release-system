package com.news.common.project.app;

import com.news.common.project.dto.NewsAdminUserDto;

public interface NewsAdminUserAppService {
	public NewsAdminUserDto getUserByName(String username);
}
