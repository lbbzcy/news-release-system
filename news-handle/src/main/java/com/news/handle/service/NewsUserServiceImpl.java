package com.news.handle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.common.core.dto.PageData;
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

	@Override
	public NewsUserDto getUserByAuth(String auth,String password) {
		return newsUserDtoMapper.getUserByAuth(auth,password);
	}

	@Override
	public PageData<NewsUserDto> findPageWithUser(PageData<NewsUserDto> pageData, NewsUserDto queryParam) {
		PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize());
		List<NewsUserDto> findListPage = newsUserDtoMapper.queryAllData(queryParam);
		Page<NewsUserDto> page = (Page<NewsUserDto>) findListPage;
		pageData.setTotal(page.getTotal());
		pageData.setRows(findListPage);
		return pageData;
	}

	@Override
	public NewsUserDto getUserById(String id) {
		return newsUserDtoMapper.selectByPrimaryKey(id);
	}

}
