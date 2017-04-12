package com.news.common.core.dto;

import java.util.Date;

/**
 *  日期快捷查询实体
 * @author zcy
 *
 */
public class QueryDateDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -678710263704572873L;
	/**
	 * 查询参数.开始日期
	 */
	private Date startTime;
	/**
	 * 查询参数.结束日期
	 */
	private Date endTime;

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
