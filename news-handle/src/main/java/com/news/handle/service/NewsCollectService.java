package com.news.handle.service;

import java.util.List;

import com.news.common.project.dto.NewsCollectDto;

public interface NewsCollectService {
	public int insert(NewsCollectDto record);

	public NewsCollectDto select(NewsCollectDto record);

	public List<NewsCollectDto> getByUserId(String userid);

	public int delete(NewsCollectDto collect);
}
