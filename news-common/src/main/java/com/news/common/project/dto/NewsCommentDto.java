package com.news.common.project.dto;

import java.util.Date;
import java.util.List;

import com.news.common.core.dto.IdentityDto;

public class NewsCommentDto extends IdentityDto{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String newsid;
    
    private String newstitle;

    private String userid;
    
    private String username;
    
    private String content;

    private Date createtime;

    private Long likenum;

    private String isdel;

    private String replayid;
    
    private List<NewsCommentDto> children;

    private String header;
    
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
        this.newsid = newsid == null ? null : newsid.trim();
    }

    public String getNewstitle() {
		return newstitle;
	}

	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}

	public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getLikenum() {
        return likenum;
    }

    public void setLikenum(Long likenum) {
        this.likenum = likenum;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }

    public String getReplayid() {
        return replayid;
    }

    public void setReplayid(String replayid) {
        this.replayid = replayid == null ? null : replayid.trim();
    }

	public List<NewsCommentDto> getChildren() {
		return children;
	}

	public void setChildren(List<NewsCommentDto> children) {
		this.children = children;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
}