package com.news.common.project.app;

import com.news.common.core.dto.PageData;
import com.news.common.project.dto.NewsBannerDto;

public interface NewsBannerAppService {
	// 根据类别id查找banner
	public NewsBannerDto findBannerById(String id);

	// 插入banner
	public int insertNewsBannerDto(NewsBannerDto record);

	// 更新banner
	public int updateNewsBannerDto(NewsBannerDto record);

	// 分页查询banner
	public PageData<NewsBannerDto> findPageWithBanner(PageData<NewsBannerDto> pageData, NewsBannerDto newsBannerDto);

	// 删除banner
	public int deleteNewsBannerDto(String id);
}
