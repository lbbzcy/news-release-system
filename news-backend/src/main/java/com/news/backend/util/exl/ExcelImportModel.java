package com.news.backend.util.exl;


/**
 * Excel导入数据模型
 * @author yu
 * @date 2015-05-28
 */
public class ExcelImportModel {
	/**
     * 标识列
     */
    private String id;
    
    /**
     * 映射实体类所在路径
     */
    private String clazzName;
    
    public ExcelImportModel(String id,String clazzName){
    	this.id = id;
    	this.clazzName = clazzName;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClazzName() {
		return clazzName;
	}

	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
    
}
