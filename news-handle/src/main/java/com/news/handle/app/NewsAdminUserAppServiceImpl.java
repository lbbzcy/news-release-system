package com.news.handle.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.common.project.app.NewsAdminUserAppService;
import com.news.common.project.dto.NewsAdminUserDto;
import com.news.handle.service.NewsAdminUserService;
@Service("newsAdminUserAppService")
public class NewsAdminUserAppServiceImpl implements NewsAdminUserAppService {

	@Autowired
	private NewsAdminUserService newsAdminUserService;
	@Override
	public NewsAdminUserDto getUserByName(String username) {
		return newsAdminUserService.getUserByName(username);
	}

}
