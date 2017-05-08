package com.news.common.project.dto;

import com.news.common.core.dto.IdentityDto;

public class NewsCollectDto extends IdentityDto{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String userId;

    private String newsId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId == null ? null : newsId.trim();
    }
}