package com.news.handle.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.common.core.dto.PageData;
import com.news.common.project.app.NewsBannerAppService;
import com.news.common.project.dto.NewsBannerDto;
import com.news.handle.service.NewsBannerService;
@Service("newsBannerAppService")
public class NewsBannerAppServiceImpl implements NewsBannerAppService {

	@Autowired
	private NewsBannerService newsBannerService;
	@Override
	public NewsBannerDto findBannerById(String id) {
		return newsBannerService.findBannerById(id);
	}

	@Override
	public int insertNewsBannerDto(NewsBannerDto record) {
		return newsBannerService.insertNewsBannerDto(record);
	}

	@Override
	public int updateNewsBannerDto(NewsBannerDto record) {
		return newsBannerService.updateNewsBannerDto(record);
	}

	@Override
	public PageData<NewsBannerDto> findPageWithBanner(PageData<NewsBannerDto> pageData, NewsBannerDto newsBannerDto) {
		return newsBannerService.findPageWithBanner(pageData, newsBannerDto);
	}

	@Override
	public int deleteNewsBannerDto(String id) {
		return newsBannerService.deleteNewsBannerDto(id);
	}

	@Override
	public List<NewsBannerDto> findByNewsId(String id) {
		return newsBannerService.findByNewsId(id);
	}

}
