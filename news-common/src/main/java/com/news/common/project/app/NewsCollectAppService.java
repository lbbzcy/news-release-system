package com.news.common.project.app;

import com.news.common.project.dto.NewsCollectDto;

public interface NewsCollectAppService {
	public int insert(NewsCollectDto record);

	public NewsCollectDto select(NewsCollectDto record);
}
