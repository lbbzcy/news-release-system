package com.news.handle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.common.project.dto.NewsUserDto;
import com.news.handle.dao.NewsUserDtoMapper;

@Service("newsUserService")
public class NewsUserServiceImpl implements NewsUserService {

	@Autowired
	private NewsUserDtoMapper newsUserDtoMapper;
	@Override
	public List<NewsUserDto> getUserByName(String username) {
		return newsUserDtoMapper.getUserByName(username);
	}

	@Override
	public List<NewsUserDto> getUserByMobile(String mobile) {
		return newsUserDtoMapper.getUserByMobile(mobile);
	}

	@Override
	public List<NewsUserDto> getUserByEmail(String email) {
		return newsUserDtoMapper.getUserByEmail(email);
	}

	@Override
	public int insertNewsUserDto(NewsUserDto newsUserDto) {
		return newsUserDtoMapper.insert(newsUserDto);
	}

}
