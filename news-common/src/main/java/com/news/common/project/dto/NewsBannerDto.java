package com.news.common.project.dto;

import java.util.Date;

import com.news.common.core.dto.IdentityDto;

public class NewsBannerDto extends IdentityDto{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String newsid;

    private String imgsrc;

    private String link;
    
    private String title;

    private Date createtime;

    private Date updatetime;

    private String isdel;

    private String creator;

    private String updator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNewsid() {
		return newsid;
	}

	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}

	public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc == null ? null : imgsrc.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }
}