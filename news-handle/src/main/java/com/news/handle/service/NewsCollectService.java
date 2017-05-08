package com.news.handle.service;

import com.news.common.project.dto.NewsCollectDto;

public interface NewsCollectService {
	public int insert(NewsCollectDto record);

	public NewsCollectDto select(NewsCollectDto record);
}
