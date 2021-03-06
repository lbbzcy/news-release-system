package com.news.handle.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.common.project.app.NewsCollectAppService;
import com.news.common.project.dto.NewsCollectDto;
import com.news.handle.service.NewsCollectService;
@Service("newsCollectAppService")
public class NewsCollectAppServiceImpl implements NewsCollectAppService {
	@Autowired
	private NewsCollectService newsCollectService;
	@Override
	public int insert(NewsCollectDto record) {
		return newsCollectService.insert(record);
	}
	@Override
	public NewsCollectDto select(NewsCollectDto record) {
		return newsCollectService.select(record);
	}
	@Override
	public List<NewsCollectDto> getByUserId(String userid) {
		return newsCollectService.getByUserId(userid);
	}
	@Override
	public int delete(NewsCollectDto collect) {
		return newsCollectService.delete(collect);
	}

}
