package com.news.common.core.utils.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.news.common.core.dto.PageData;

public class PaginationInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PageData<?> pageData;

	public PaginationInfo(PageData<?> pageData) {
		if(pageData == null){
			throw new IllegalArgumentException("分页对象不能为空");
		}
		this.pageData = pageData;
	}

	/**
	 * 第一页
	 * @return
	 */
	public int getFirstPage(){
		return 1;
	}
	/**
	 * 返回上一页
	 * @return
	 */
	public int getPrePage(){
		if(pageData.getPageNumber() <= 1){
			return 1;
		}
		return pageData.getPageNumber() - 1;
	}
	/**
	 * 返回下一页
	 * @return
	 */
	public long getNextPage(){
		//总页数
		long totalPageNum = 0;
		if(pageData.getTotal() % pageData.getPageSize() == 0){
			totalPageNum = pageData.getTotal() / pageData.getPageSize();
		} else {
			totalPageNum =  pageData.getTotal() / pageData.getPageSize() + 1;
		}
		if(pageData.getPageNumber() >= totalPageNum) {
			return totalPageNum;
		} else {
			return pageData.getPageNumber() + 1;
		}
	}
	/**
	 * 最后一页
	 * @return
	 */
	public long getLastPage(){
		long totalPageNum = 0;
		if(pageData.getTotal() % pageData.getPageSize() == 0){
			totalPageNum = pageData.getTotal() / pageData.getPageSize();
		} else {
			totalPageNum =  pageData.getTotal() / pageData.getPageSize() + 1;
		}
		//当总记录数据为0时，则只有1页
		if(totalPageNum == 0){
			totalPageNum = 1;
		}
		return totalPageNum;
	}
	/**
	 * 当前页
	 * @return
	 */
	public int getCurrentPage(){
		return pageData.getPageNumber();
	}
	/**
	 * 每页显示多少条记录
	 * @return
	 */
	public int getPageSize(){
		return pageData.getPageSize();
	}
	/**
	 * 总页数
	 * @return
	 */
	public long getTotal(){
		return pageData.getTotal();
	}
	/**
	 * 返回页码列表
	 * @return
	 */
	public List<String> getPageNumList(){
		List<String> nums = new ArrayList<String>();
		int currentPage = getCurrentPage();
		long totalPage = getLastPage();
		if(totalPage <= 15){
			//如果总页数小于等于15页，则所有页码都显示出来
			for(int i = 1 ; i <=totalPage ; i++){
				nums.add(String.valueOf(i));
			}
		} else {
			if(currentPage <= 7){
				//显示前9位数据+...+最后一位数据
				for(int i = 1; i <= 9; i++){
					nums.add(String.valueOf(i));
				}
				nums.add("_" + (totalPage+9)/2);
				nums.add(String.valueOf(totalPage));
			} else if(currentPage >= (totalPage-7)) {
				//显示第一个数字+...+后9位
				nums.add("1");
				nums.add("_" + (totalPage-8)/2);
				for(long i = totalPage-8  ; i <=totalPage ; i ++){
					nums.add(String.valueOf(i));
				}
			} else {
				//否则当前页前减4，后加3,
				int first = currentPage-4;
				int end =  currentPage + 3;
				nums.add("1");
				nums.add("_" + (first+1)/2);
				for(int i = first ; i <= end ; i++){
					nums.add(String.valueOf(i));
				}
				nums.add("_" + (totalPage+end)/2);
				nums.add(String.valueOf(totalPage));
			}
		}
		return nums;
	}
}
