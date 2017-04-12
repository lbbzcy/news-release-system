package com.news.common.core.dto;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class PageData<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long total = 0L;
	private int pageSize = 15;
	private int pageNumber = 1;
	private List<T> rows = null;

	public static <T> PageData<T> createPagerData(int pageSize, int pageNumber) {
		PageData<T> data = new PageData<T>();
		data.setPageSize(pageSize);
		data.setPageNumber(pageNumber);
		return data;
	}

	public static <T> PageData<T> createPagerData(HttpServletRequest request) {
		PageData<T> data = new PageData<T>();
		if (request.getParameter("pageSize") != null) {
			data.setPageSize(Integer.valueOf(request.getParameter("pageSize")).intValue());
		}

		if (request.getParameter("pageNumber") != null) {
			data.setPageNumber(Integer.valueOf(request.getParameter("pageNumber")).intValue());
		}

		return data;
	}

	public long getTotal() {
		return this.total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public List<T> getRows() {
		return this.rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getEndIndex() {
		return this.getPageNumber() * this.getPageSize();
	}

	public int getBeginIndex() {
		return this.getEndIndex() - this.getPageSize() + 1;
	}
}
