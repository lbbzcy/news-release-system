package com.news.backend.util.exl;


import java.util.List;

/**
 * Excel解析结果Bean
 * @author yu
 *
 */
public class ExcelImportParserResult {
	/**
	 * 消息代号：（0000->处理成功，5555->校验出现了错误）
	 */
	private String messageCode;
	/**
	 * 消息内容
	 */
	private String messageContext;
	/**
	 * 如果messageCode='0000',resultList 存放Excel数据
	 * 如果messageCode='5555',resultList 存放的是验证失败的结果集合（为实体Bean,ExcelError的List集合）
	 */
	private List<?> resultList;
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public List<?> getResultList() {
		return resultList;
	}
	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}
	public String getMessageContext() {
		return messageContext;
	}
	public void setMessageContext(String messageContext) {
		this.messageContext = messageContext;
	}
}
