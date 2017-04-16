package com.news.backend.util.exl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Excel 配置管理类初始话
 * 
 * @author yu
 * 
 */
public class ExcelConfigManager {
	private static Logger log = LoggerFactory
			.getLogger(ExcelConfigManager.class);

	/**
	 * Excel导出映射配置文件
	 */
	private static final String EXPORT_CONFIGNAME = "excel/ExcelExport-mapping.xml";

	/**
	 * Excel导入映射配置文件
	 */
	private static final String IMPORT_CONFIGNAME = "excel/ExcelImport-mapping.xml";

	/**
	 * Excel导出属性模型集合
	 */
	private static Map<String, List<ExcelExportModelProperty>> excelExportModelPropertyMap = new HashMap<String, List<ExcelExportModelProperty>>();

	/**
	 * Excel导出模型
	 */
	private static Map<String, ExcelExportModel> excelExportModelMap = new HashMap<String, ExcelExportModel>();

	/**
	 * Excel导入属性模型集合
	 */
	private static Map<String, List<ExcelImportModelProperty>> excelImportModelPropertyMap = new HashMap<String, List<ExcelImportModelProperty>>();

	/**
	 * Excel导入模型
	 */
	private static Map<String, ExcelImportModel> excelImportModelMap = new HashMap<String, ExcelImportModel>();

	static {
		/**
		 * 加载Excel导出XML文件，ExcelExport-mapping.xml
		 */
		init(EXPORT_CONFIGNAME);
		/**
		 * 加载Excel导出XML文件，ExcelImport-mapping.xml
		 */
		init(IMPORT_CONFIGNAME);
	}

	/**
	 * 将Excel的导入导出配置文件读到内存
	 */
	public static void init(String configName) {
		log.debug("开始加载‘{}’", configName);
		InputStream in = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(configName);
		Document document = ExcelXMLUtil.getXMLDocument(in);
		if (EXPORT_CONFIGNAME.equals(configName)) {
			excelExportModelPropertyMap = ExcelXMLUtil.parserExcelExportXml(document);
			excelExportModelMap = ExcelXMLUtil.getModelNameMap();
		} else if (IMPORT_CONFIGNAME.equals(configName)) {
			excelImportModelPropertyMap = ExcelXMLUtil
					.parserExcelImportXml(document);
			excelImportModelMap = ExcelXMLUtil.getImportModelNameMap();
		}
		log.debug("‘{}’已加载完成", configName);
	}

	/**
	 * 根据定义的模型名获取对应的数据模型集合
	 * 
	 * @param modelName
	 *            模型ID
	 * @return List<ExcelModel>
	 */
	public static List<ExcelExportModelProperty> getExcelExportModelProperty(
			String modelName) {
		List<ExcelExportModelProperty> list = new ArrayList<ExcelExportModelProperty>();
		list = excelExportModelPropertyMap.get(modelName);
		if (list == null || list.size() == 0) {
			log.info("输入的模型名称有误，请检查模型名【" + modelName
					+ "】是否存在于配置文件\"ExcelExport-mapping.xml\"中");
		}
		return list;
	}

	/**
	 * 获取Excel的SheetName名称
	 * 
	 * @param modelName
	 *            模型ID
	 * @return
	 */
	public static ExcelExportModel getExcelExportModel(String modelName) {
		ExcelExportModel model = null;
		model = excelExportModelMap.get(modelName);
		if (model == null) {
			log.info("输入的模型名称有误，请检查模型名【" + modelName
					+ "】是否存在于配置文件\"ExcelExport-mapping.xml\"中");
		}
		return model;
	}

	/**
	 * 根据定义的模型名获取对应的数据模型集合
	 * 
	 * @param modelName
	 *            模型ID
	 * @return List<ExcelModel>
	 */
	public static List<ExcelImportModelProperty> getExcelImportModelProperty(
			String modelName) {
		List<ExcelImportModelProperty> list = new ArrayList<ExcelImportModelProperty>();
		list = excelImportModelPropertyMap.get(modelName);
		if (list == null || list.size() == 0) {
			log.info("输入的模型名称有误，请检查模型名【" + modelName
					+ "】是否存在于配置文件\"ExcelImport-mapping.xml\"中");
		}
		return list;
	}

	/**
	 * 根据模型名称，获取对应的ExcelImportModel
	 * 
	 * @param modelName
	 *            模型ID
	 * @return
	 */
	public static ExcelImportModel getExcelImportModel(String modelName) {
		ExcelImportModel model = null;
		model = excelImportModelMap.get(modelName);
		if (model == null) {
			log.info("输入的模型名称有误，请检查模型名【" + modelName
					+ "】是否存在于配置文件\"ExcelImport-mapping.xml\"中");
		}
		return model;
	}
}

