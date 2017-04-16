package com.news.backend.util.exl;

import java.util.List;

/**
 * Excel导入属性模型
 * @author yu
 * @date 2016-05-28
 */
public class ExcelImportModelProperty {
	private String name;					//字段属性名
	private String dataType;				//数据类型
	private String isEmpty;					//是否允许为空
	private String excelTitleName;			//Excel标题名字
	private String codeTableName;			//数据字典名称
	private String defaultValue;			//默认值
	private List<Rule> ruleList;			//校验规则
	private int column;						//表示第几列（按照存的顺序进行排列，先进先出的原则）
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getIsEmpty() {
		return isEmpty;
	}
	public void setIsEmpty(String isEmpty) {
		this.isEmpty = isEmpty;
	}
	public String getExcelTitleName() {
		return excelTitleName;
	}
	public void setExcelTitleName(String excelTitleName) {
		this.excelTitleName = excelTitleName;
	}
	public String getCodeTableName() {
		return codeTableName;
	}
	public void setCodeTableName(String codeTableName) {
		this.codeTableName = codeTableName;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public List<Rule> getRuleList() {
		return ruleList;
	}
	public void setRuleList(List<Rule> ruleList) {
		this.ruleList = ruleList;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
}

/**
 * 验证规则
 * @author lhui
 *
 */
class Rule{
	private String type;					//规则类型
	private String regexValue;				//校验的正则表达式值
	private String errorMessage;			//校验错误时，所展现的错误信息
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getRegexValue() {
		return regexValue;
	}
	public void setRegexValue(String regexValue) {
		this.regexValue = regexValue;
	}
}