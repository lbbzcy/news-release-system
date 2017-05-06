package com.news.common.project.dto;

import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

import com.news.common.core.constant.EYesNo;
import com.news.common.core.dto.IdentityDto;

public class NewsDetailDto extends IdentityDto{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Field("id")
	private String id;
	@Field("typeid")
    private String typeid;
	@Field("templateid")
    private String templateid;
	@Field("typename")
    private String typename;
	@Field("templatename")
    private String templatename;
	@Field("newstitle")
    private String title;
	@Field("viewnum")
    private Long viewnum;
    @Field("commentnum")
    private Long commentnum;
   
    private EYesNo ishot;
    @Field("creator")
    private String creator;
    @Field("updator")
    private String updator;
    @Field("createtime")
    private Date createtime;
    @Field("updatetime")
    private Date updatetime;
    @Field("isdel")
    private String isdel;
	@Field("newscontent")
    private String content;
	@Field("brief")
    private String brief;
    
    //查询条件
    private Date startTime;
    private Date endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getTemplateid() {
        return templateid;
    }

    public void setTemplateid(String templateid) {
        this.templateid = templateid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getViewnum() {
        return viewnum;
    }

    public void setViewnum(Long viewnum) {
        this.viewnum = viewnum;
    }

    public Long getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(Long commentnum) {
        this.commentnum = commentnum;
    }

    public EYesNo getIshot() {
        return ishot;
    }

    public void setIshot(EYesNo ishot) {
        this.ishot = ishot;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getTemplatename() {
		return templatename;
	}

	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}