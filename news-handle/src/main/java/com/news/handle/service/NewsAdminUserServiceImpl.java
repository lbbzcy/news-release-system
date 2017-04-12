package com.news.handle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.news.common.project.dto.NewsAdminUserDto;
import com.news.handle.common.Constants;
import com.news.handle.dao.NewsAdminUserDtoMapper;
@Service("newsAdminUserService")
@Transactional(value=Constants.TX_MANAGER_NEWS)
public class NewsAdminUserServiceImpl implements NewsAdminUserService {
	
	@Autowired
	private NewsAdminUserDtoMapper newsAdminUserDtoMapper;
	@Override
	public NewsAdminUserDto getUserByName(String username) {
		return newsAdminUserDtoMapper.getUserByName(username);
	}

}
