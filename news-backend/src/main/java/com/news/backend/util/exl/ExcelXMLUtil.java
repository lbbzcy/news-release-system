package com.news.backend.util.exl;


import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.news.common.core.utils.ExceptionProcUtil;

/**
 * 解析Excel实体映射XML工具类
 * @author yu
 *
 */
public class ExcelXMLUtil
{
    private static Logger log = LoggerFactory.getLogger(ExcelXMLUtil.class);
    
    private static Map<String, ExcelExportModel> modelNameMap = new HashMap<String, ExcelExportModel>();
    
    private static Map<String, ExcelImportModel> importModelNameMap = new HashMap<String, ExcelImportModel>();
    
    private static final String _DEFAULT_ALIGN = "center";
    
    private static final String _DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 将一个XML字符串转发成Document对象
     * 
     * @param xmlStr XML字符串
     * @return Document
     */
    public static Document getXMLDocument(String xmlStr)
    {
        Document document = null;
        try
        {
            document = DocumentHelper.parseText(xmlStr);
        }
        catch (Exception e)
        {
            log.error(new StringBuffer("[").append(ExceptionProcUtil.getExceptionDesc(e)).append("]").append(e.getCause()).toString());
        }
        return document;
    }
    
    /**
     * 将一个XML文件转发成Document对象
     * 
     * @param xmlFile XML文件
     * @return Document
     */
    public static Document getXMLDocument(File xmlFile)
    {
        Document document = null;
        try
        {
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(xmlFile); // 获取文档结构对象
        }
        catch (Exception e)
        {
            log.error(new StringBuffer("[").append(ExceptionProcUtil.getExceptionDesc(e)).append("]").append(e.getCause()).toString());
        }
        return document;
    }
    
    /**
     * 将一个XML文件的输入流转发成Document对象
     * 
     * @param xmlFile XML文件
     * @return Document
     */
    public static Document getXMLDocument(InputStream in)
    {
        Document document = null;
        try
        {
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(in); // 获取文档结构对象
        }
        catch (Exception e)
        {
            log.error(new StringBuffer("[").append(ExceptionProcUtil.getExceptionDesc(e)).append("]").append(e.getCause()).toString());
        }
        return document;
    }
    
    /**
     * 解析EXCEL导出配置文件
     * 
     * @param document 文档对象
     * @return Map<String, List<ExcelModel>>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, List<ExcelExportModelProperty>> parserExcelExportXml(Document document)
    {
        Map<String, List<ExcelExportModelProperty>> map = new HashMap<String, List<ExcelExportModelProperty>>();
        Element root = document.getRootElement();// 获取跟元素
        for (Iterator<Element> i = root.elementIterator(); i.hasNext();)
        {
            List<ExcelExportModelProperty> excelModelList = new ArrayList<ExcelExportModelProperty>();
            Element firstElement = i.next();// 获取跟元素下的子元素
            String modelName = firstElement.attributeValue("id");
            String sheetName = firstElement.attributeValue("sheetName");
            String title = firstElement.attributeValue("title");
            for (Iterator<Element> j = firstElement.elementIterator(); j.hasNext();)
            {
                ExcelExportModelProperty model = new ExcelExportModelProperty();
                Element secondElement = j.next();// 获取二级元素
                String name = secondElement.attributeValue("name"); // 实体属性字段名
                String excelTitleName = secondElement.attributeValue("excelTitleName"); // Excel标题名称
                String dataType = secondElement.attributeValue("dataType"); // 数据类型
                String align = secondElement.attributeValue("align"); // 对齐方式
                String format = secondElement.attributeValue("format"); // 日期格式化字符串
                String defaultValue = secondElement.attributeValue("defaultValue"); // 默认值
                String columnWidth = secondElement.attributeValue("columnWidth");// 列宽
                String autoLine = secondElement.attributeValue("autoLine");// 是否自动换行
                // 为ExcelModelProperty Bean中的属性赋值
                model.setName(name);
                model.setExcelTitleName(excelTitleName);
                model.setDataType(dataType);
                model.setFormat(format == null || "".equals(format) ? _DEFAULT_DATE_FORMAT : format);
                model.setDefaultValue(defaultValue);
                model.setAlign(align == null || "".equals(align) ? _DEFAULT_ALIGN : align);
                model.setColumnWidth(columnWidth == null || "".equals(columnWidth)
                    ? -1 : Integer.valueOf(columnWidth));
                model.setAutoLine(autoLine == null || "".equals(autoLine)
                    ? false : Boolean.valueOf(autoLine));
                // 设置默认值
                setDefaultValue(model);
                
                excelModelList.add(model);
            }
            map.put(modelName, excelModelList);
            
            ExcelExportModel model = new ExcelExportModel(modelName, sheetName, title);
            modelNameMap.put(modelName, model);
        }
        return map;
    }
    
    /**
     * 解析EXCEL导入配置文件
     * 
     * @param document 文档对象
     * @return Map<String, List<ExcelModel>>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, List<ExcelImportModelProperty>> parserExcelImportXml(Document document){
    	Map<String,List<ExcelImportModelProperty>> map = new HashMap<String, List<ExcelImportModelProperty>>();
		Element root = document.getRootElement();//获取跟元素
		for(Iterator<Element> i = root.elementIterator(); i.hasNext();){
			List<ExcelImportModelProperty> excelModelList = new ArrayList<ExcelImportModelProperty>();
			Element firstElement = i.next();//获取跟元素下的子元素
			String modelName = firstElement.attributeValue("id");
			String clazzName = firstElement.attributeValue("class");
			int column = 1;
			for(Iterator<Element> j = firstElement.elementIterator(); j.hasNext();){
				ExcelImportModelProperty model = new ExcelImportModelProperty();
				Element secondElement = j.next();//获取二级元素
				String name = secondElement.attributeValue("name");						//实体属性字段名
				String dataType = secondElement.attributeValue("dataType");				//实体属性类型
				String isEmpty = secondElement.attributeValue("isEmpty");				//该属性是否运行为空
				String excelTitleName = secondElement.attributeValue("excelTitleName");	//Excel标题名称
				String codeTableName = secondElement.attributeValue("codeTableName");	//数据字典名称
				String defaultValue = secondElement.attributeValue("defaultValue");		//实体属性的默认值
				//为ExcelModel Bean中的属性赋值
				model.setName(name);
				model.setDataType(dataType);
				model.setIsEmpty(isEmpty);
				model.setExcelTitleName(excelTitleName);
				model.setCodeTableName(codeTableName);
				model.setDefaultValue(defaultValue);
				model.setColumn(column);
				List<Rule> ruleList = new ArrayList<Rule>();
				for(Iterator<Element> k = secondElement.elementIterator(); k.hasNext();){
					Element thirdElement = k.next();//获取三级元素
					Rule rule = new Rule();
					String type = thirdElement.attributeValue("type");
					rule.setType(type);
					if("REGEX".equalsIgnoreCase(type)){//表示采用自定义正则表达式校验
						String regexValue = thirdElement.attributeValue("value");
						rule.setRegexValue(regexValue);
						rule.setErrorMessage("数据格式非法");
					}else{
						rule.setRegexValue(RegexEnum.valueOf(type).getRegexValue());
						rule.setErrorMessage(RegexEnum.valueOf(type).getErrorMessage());
					}
					ruleList.add(rule);
				}
				model.setRuleList(ruleList);
				excelModelList.add(model);
				column++;
			}
			map.put(modelName, excelModelList);
			ExcelImportModel importModel = new ExcelImportModel(modelName,clazzName);
			importModelNameMap.put(modelName, importModel);
		}
		return map;
    }
    
    /**
     * 判断是否设置了默认值，没有则进行设置
     * 
     * @param model
     */
    private static void setDefaultValue(ExcelExportModelProperty model)
    {
        if (model.getDefaultValue() == null || "".equals(model.getDefaultValue()))
        {
            if ("int".equalsIgnoreCase(model.getDataType()))
            {
                model.setDefaultValue(0);
            }
            else if ("double".equalsIgnoreCase(model.getDataType()))
            {
                model.setDefaultValue(0.00);
            }
            else
            {
                model.setDefaultValue("");
            }
        }
    }
    
    /**
     * 需要导出ExcelSheet名称集合
     * 
     * @return sheetNameMap
     */
    public static Map<String, ExcelExportModel> getModelNameMap()
    {
        return modelNameMap;
    }

	public static Map<String, ExcelImportModel> getImportModelNameMap() {
		return importModelNameMap;
	}
}
