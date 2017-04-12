package com.news.handle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.common.core.dto.PageData;
import com.news.common.project.dto.NewsBannerDto;
import com.news.handle.dao.NewsBannerDtoMapper;
@Service("newsBannerService")
public class NewsBannerServiceImpl implements NewsBannerService {
	@Autowired
	private NewsBannerDtoMapper newsBannerDtoMapper;
	@Override
	public NewsBannerDto findBannerById(String id) {
		return newsBannerDtoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertNewsBannerDto(NewsBannerDto record) {
		return newsBannerDtoMapper.insert(record);
	}

	@Override
	public int updateNewsBannerDto(NewsBannerDto record) {
		return newsBannerDtoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public PageData<NewsBannerDto> findPageWithBanner(PageData<NewsBannerDto> pageData, NewsBannerDto newsBannerDto) {
		PageHelper.startPage(pageData.getPageNumber(), pageData.getPageSize());
		List<NewsBannerDto>  findListPage = newsBannerDtoMapper.queryAllData(newsBannerDto);
		Page<NewsBannerDto> page = (Page<NewsBannerDto>) findListPage;
		pageData.setTotal(page.getTotal());
		pageData.setRows(findListPage);
		return pageData;
	}

	@Override
	public int deleteNewsBannerDto(String id) {
		return newsBannerDtoMapper.deleteByPrimaryKey(id);
	}

}
