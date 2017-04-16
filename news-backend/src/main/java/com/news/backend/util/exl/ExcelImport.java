package com.news.backend.util.exl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.news.common.core.exception.ApplicationException;
import com.news.common.core.utils.ExceptionProcUtil;
import com.news.common.core.utils.ReflectionUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public class ExcelImport {
	//记录日志信息
	private static final Logger log = LoggerFactory.getLogger(ExcelImport.class);
	//用来定义Excel错误源
	public static String errArr[] = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	//位置模板
	public static String POSITION_TEMPLATE = "[POSITION]第ROW行第CELL列";
	//是否需要给用户提供错误文件
	@SuppressWarnings("unused")
	private static boolean errFileDownload = false;
	
	//成功的处理码
	public static String MESSAGE_CODE_0000 = "0000";
	//失败的处理码
	public static String MESSAGE_CODE_5555 = "5555";
	
	/**
	 * 解析Excel，将Excel的数据读取到List集合
	 * @param filePath 文件路径
	 * @param modelName 在配置文件“ImportExcelToModel.xml”中的模型ID名称
	 * @param errorList 输出参数 
	 * @return
	 * @throws Exception 
	 */
	public static ExcelImportParserResult importExcel(String filePath,String modelName) throws ApplicationException{
		ExcelImportParserResult result = new ExcelImportParserResult();
		List dataList = new ArrayList();
		//校验Excel文件行格式是否与模板文件一致
		validataDataRow(filePath, modelName);
		//读取Excel文件
		try {
			dataList = readExcelToList(filePath, modelName);
			//验证Excel文件中的数据是否合法
			List<ExcelImportError> errorList = validataDataCell(dataList, modelName);
			if(errorList!=null && errorList.size()>0){//表示Excel文件中有非法数据
				result.setMessageCode(MESSAGE_CODE_5555);
				result.setResultList(errorList);
			}else{
				result.setMessageCode(MESSAGE_CODE_0000);
				result.setResultList(dataList);
			}
		} catch (Exception ex) {
			log.error("解析Excel文件出现异常:"+ExceptionProcUtil.getExceptionDesc(ex));
			throw new ApplicationException("解析Excel文件出现异常");
		}
		return result;
	}
	/**
	 * 检查Exce的数据行
	 * @param filePath
	 * @return
	 * @throws Exception 
	 */
	public static void validataDataRow(String filePath,String modelName) throws ApplicationException{
		List<ExcelImportModelProperty> modelList = ExcelConfigManager.getExcelImportModelProperty(modelName);
		//根据类的名称通过反射机制获取相应的实例
		ExcelImportModelProperty model = null;
		Workbook wb = getWorkBook(filePath);
		//获取第一个SHEET
		Sheet sheet = wb.getSheetAt(0);
		//获取总行数
		int totalRow = sheet.getPhysicalNumberOfRows();
		//获取总列数
		int totalCell = 0;
		if(totalRow>=1 && sheet.getRow(0)!=null){
			totalCell = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		if(totalRow == 1){
			throw new ApplicationException("您导入的Excel文件没有任何业务数据");
		}
		if(totalCell!=modelList.size()){
			throw new ApplicationException("您导入的Excel文件列数与提供的模板文件不一致！");
		}
		String excelTitle = "";
		for(int i=0;i<totalCell;i++){
			model = modelList.get(i);
			excelTitle=sheet.getRow(0).getCell(i).getStringCellValue();
			if(!model.getExcelTitleName().equals(excelTitle)){
				throw new ApplicationException("请确保您导入的Excel文件首行标题是否与提供的模板文件一致！");
			}
		}
	}
	
	/**
	 * 校验Excel数据的列值
	 * @param dataList
	 * @param modelName
	 * @return
	 * @throws Exception
	 */
	public static List<ExcelImportError> validataDataCell(List<?> dataList,String modelName) throws Exception{
		List<ExcelImportModelProperty> modelList = ExcelConfigManager.getExcelImportModelProperty(modelName);
		//根据类的名称通过反射机制获取相应的实例
		List<ExcelImportError> errorList = new ArrayList<ExcelImportError>();
		for(int i=0;i<dataList.size();i++){
			Object target = dataList.get(i);
			ExcelImportModelProperty model = null;
			for(int j=0;j<modelList.size();j++){
				ExcelImportError error = new ExcelImportError();
				model = modelList.get(j);
				String fieldValue = String.valueOf(ReflectionUtil.reflectGetValue(target, model.getName()));
				error.setCellName(model.getExcelTitleName());
				error.setCellValue(fieldValue);
				error.setRow(i+2);
				error.setCell(model.getColumn());
				String cellPosition = POSITION_TEMPLATE.replace("POSITION", errArr[model.getColumn()-1]+(i+2)).replace("ROW", error.getRow()+"").replace("CELL", error.getCell()+"");
				error.setCellPosition(cellPosition);
				if("Y".equals(model.getIsEmpty())){//表示该属性不能为空
					if(!StringUtils.isNotBlank(fieldValue)){
						error.setCellMessage("\""+model.getExcelTitleName()+"\"，不允许为空");
						errorList.add(error);
						continue;
					}
				}
				List<Rule> ruleList = model.getRuleList();
				if(ruleList!=null && ruleList.size()>0){
					for(Rule rule : ruleList){
						if(!fieldValue.matches(rule.getRegexValue())){
							error.setCellMessage("\""+model.getExcelTitleName()+"\"，"+rule.getErrorMessage());
							errorList.add(error);
							break;
						}
					}
				}
			}
		}
		return errorList;
	}
	
	/**
	 * 将Excel的数据导入到LIST集合
	 * @param filePath 要读取的Excel文件路径
	 * @param modelName 在配置文件“ImportExcelToModel.xml”中的模型ID名称
	 * @return
	 * @throws Exception
	 */
	public static List<?> readExcelToList(String filePath,String modelName) throws Exception{
		List dataList = new ArrayList();
		List<ExcelImportModelProperty> modelList = ExcelConfigManager.getExcelImportModelProperty(modelName);
		ExcelImportModel importModel = ExcelConfigManager.getExcelImportModel(modelName);
		//根据类的名称通过反射机制获取相应的实例
		Class<?> clazz = Class.forName(importModel.getClazzName());
		
		ExcelImportModelProperty model = null;
		Workbook wb = getWorkBook(filePath);
		//获取第一个SHEET
		Sheet sheet = wb.getSheetAt(0);
		//获取总行数
		int totalRow = sheet.getPhysicalNumberOfRows();
		//获取总列数
		int totalCell = 0;
		if(totalRow>=1 && sheet.getRow(0)!=null){
			totalCell = sheet.getRow(0).getPhysicalNumberOfCells();
		}
		int i,j;
		for(i = 1;i<totalRow;i++){
			Row row = sheet.getRow(i);
			if(row==null){
				continue;
			}
			Object obj = clazz.newInstance();
			for(j = 0;j<totalCell;j++){
				Cell cell = row.getCell(j);
				model = modelList.get(j);
				Object cellValue = null;
				if(cell == null){
					ReflectionUtil.reflectSetValue(obj, model.getName(), cellValue);
					continue;
				}
				//处理数字型，自动去零
				if(Cell.CELL_TYPE_NUMERIC == cell.getCellType()){
					if(HSSFDateUtil.isCellDateFormatted(cell)){
						cellValue = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
					}else{
						if("String".equals(model.getDataType())){
							double tempNum =cell.getNumericCellValue();
							DecimalFormat  df=new DecimalFormat("###########.##");
							cellValue=df.format(tempNum);
						}else{
							cellValue = cell.getNumericCellValue();
						}
					}
				}else if(Cell.CELL_TYPE_STRING == cell.getCellType()){//处理字符串
					cellValue = cell.getStringCellValue();
				}else if(Cell.CELL_TYPE_BOOLEAN == cell.getCellType()){//处理布尔型数据
					cellValue = String.valueOf(cell.getBooleanCellValue());
				}else if(Cell.CELL_TYPE_FORMULA == cell.getCellType()){//处理公式型数据
					cellValue = cell.getNumericCellValue();
					if(cellValue.equals("NaN")){
						cellValue = cell.getRichStringCellValue();
					}
				}else{
					cellValue = cell.getStringCellValue();
				}
				ReflectionUtil.reflectSetValue(obj, model.getName(), cellValue);
			}
			dataList.add(obj);
		}
		return dataList;
	}
	
	/**
	 * 计算Excel的实际行数
	 * @param sheet
	 * @param totalRow
	 * @return
	 */
	@SuppressWarnings("unused")
	private static int calcRealRow(Sheet sheet,int totalRow,int totalCell){
		int tempCount = 0;//计数器
		Row lastRow = sheet.getRow(totalRow-1);//最后一行
		for(int m = 0;m<totalCell;m++){
			Cell lastCell = lastRow.getCell(m);
			if(lastCell == null){
				tempCount++;
			}else{
				if(Cell.CELL_TYPE_NUMERIC != lastCell.getCellType()){
					if("".equals(lastCell.getStringCellValue().replaceAll(" ", ""))){
						tempCount++;
					}
				}
			}
		}
		if(tempCount == totalCell){
			totalRow = totalRow - 1;
		}
		return totalRow;
	}
	/**
	 * 根据文件的后缀，获取Workbook Object（由于Office2003和Office2007、2010的处理方式不一样）
	 * @param filePath
	 * @return Workbook
	 * @throws Exception
	 */
	public static Workbook getWorkBook(String filePath) throws ApplicationException{
		boolean isExcel2003 = true;//是否是Excel 2003
		Workbook wb = null;
		String houzhui = filePath.substring(filePath.lastIndexOf("."),filePath.length());
		if(".xls".equals(houzhui) || ".xlsx".equals(houzhui)){
			if(".xlsx".equals(houzhui)){
				isExcel2003=false;
			}
		}else{
			throw new ApplicationException("只支持xls或者xlsx文件格式的导入");
		}
		try{
			File file = new File(filePath);
			if(file==null || !file.exists()){
				throw new ApplicationException("该文件不存在");
			}
			FileInputStream fis = new FileInputStream(file);
			wb = isExcel2003 ? new HSSFWorkbook(fis) : new XSSFWorkbook(fis);
		}catch(Exception ex){
			throw new ApplicationException("获取Workbook对象出现异常");
		}
		return wb;
	}
	
	/**
	 * 功能：将HSSFWorkbook写入Excel文件
	 * 
	 * @param wb
	 *            HSSFWorkbook
	 * @param absPath
	 *            写入文件的相对路径
	 * @param wbName
	 *            文件名
	 */
	public static void writeWorkbook(HSSFWorkbook wb, String fileName) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName);
			wb.write(fos);
			fos.flush();
		} catch (FileNotFoundException e) {
			log.error(new StringBuffer("[").append(ExceptionProcUtil.getExceptionDesc(e)).append("]")
					.append(e.getCause()).toString());
		} catch (IOException e) {
			log.error(new StringBuffer("[").append(ExceptionProcUtil.getExceptionDesc(e)).append("]")
					.append(e.getCause()).toString());
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				log.error(new StringBuffer("[").append(ExceptionProcUtil.getExceptionDesc(e)).append(
						"]").append(e.getCause()).toString());
			}
		}
	}
	
	/**
	 * 功能：创建HSSFSheet工作簿
	 * 
	 * @param wb
	 *            HSSFWorkbook
	 * @param sheetName
	 *            String
	 * @return HSSFSheet
	 */
	public static HSSFSheet createSheet(HSSFWorkbook wb, String sheetName) {
		HSSFSheet sheet = wb.createSheet(sheetName);
		sheet.setDefaultColumnWidth(12);
		sheet.setGridsPrinted(true);
		sheet.setDisplayGridlines(true);
		return sheet;
	}
	
	/**
	 * 功能：创建HSSFRow
	 * 
	 * @param sheet
	 *            HSSFSheet
	 * @param rowNum
	 *            int
	 * @param height
	 *            int
	 * @return HSSFRow
	 */
	public static HSSFRow createRow(HSSFSheet sheet, int rowNum) {
		HSSFRow row = sheet.createRow(rowNum);
		return row;
	}
	
	/**
	 * 功能：创建CELL
	 * 
	 * @param cellNum
	 *            int
	 * @param style
	 *            HSSFStyle
	 * @return HSSFCell
	 */
	public static HSSFCell createCell(HSSFRow row, int cellNum, CellStyle style) {
		HSSFCell cell = row.createCell(cellNum);
		cell.setCellStyle(style);
		return cell;
	}
	
	/**
	 * 功能：创建带边框的CellStyle样式
	 * 
	 * @param wb
	 *            HSSFWorkbook
	 * @param backgroundColor
	 *            背景色
	 * @param foregroundColor
	 *            前置色
	 * @param halign 对齐方式
	 * @return CellStyle
	 */
	public static CellStyle createBorderCellStyle(HSSFWorkbook wb,
			short backgroundColor, short foregroundColor, short halign) {
		CellStyle cs = wb.createCellStyle();
		cs.setAlignment(halign);
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cs.setFillBackgroundColor(backgroundColor);
		cs.setFillForegroundColor(foregroundColor);
		cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		return cs;
	}
	
	/**
	 * 将集合数据写入到Excel文件
	 * @param dataList
	 * @param errorList
	 * @param modelName
	 * @throws Exception
	 */
	public static void writeExcelByList(List dataList,List<ExcelImportError> errorList,String modelName) throws Exception {
		List<ExcelImportModelProperty> modelList = ExcelConfigManager.getExcelImportModelProperty(modelName);
		//根据类的名称通过反射机制获取相应的实例
		Map<String, ExcelImportError> errorMap = getRowCellValue(errorList);
		//String filePath = FileUtil.getContextPath("upload");
		String filePath = "";
		filePath = filePath+modelName+"_ERROR.xls";
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = createSheet(wb, modelName);
		HSSFRow row = createRow(sheet, 0);
		HSSFPatriarch patr = sheet.createDrawingPatriarch();
		//背景色为红色
		CellStyle redStyle = createBorderCellStyle(wb, HSSFColor.RED.index, HSSFColor.RED.index, CellStyle.ALIGN_CENTER);
		//背景色为灰色
		CellStyle darkgrayStyle = createBorderCellStyle(wb, HSSFColor.GREY_50_PERCENT.index, HSSFColor.GREY_50_PERCENT.index, CellStyle.ALIGN_CENTER);
		//背景色为白色
		CellStyle whiteStyle = createBorderCellStyle(wb, HSSFColor.WHITE.index, HSSFColor.WHITE.index, CellStyle.ALIGN_CENTER);
		//创建单元格
		HSSFCell cell = null;
		for(int i=0;i<modelList.size();i++){
			ExcelImportModelProperty model = modelList.get(i);
			cell = createCell(row, i, darkgrayStyle);
			cell.setCellValue(model.getExcelTitleName());
		}
		for(int j=0;j<dataList.size();j++){
			Object target = dataList.get(j);
			row = createRow(sheet, j+1);
			for(int k=0;k<modelList.size();k++){
				ExcelImportModelProperty model = modelList.get(k);
				Object fieldValue = ReflectionUtil.reflectGetValue(target, model.getName());
				boolean result = isFiledError(errorMap, j+2, k+1);
				if(result){
					cell = createCell(row, k, redStyle);
					HSSFComment comment = patr.createComment(new HSSFClientAnchor(0, 0, 0, 0,(short) 1, 1, (short) 3, 5));
					String key = (j+2)+"-"+(k+1);
					comment.setString(new HSSFRichTextString(errorMap.get(key).getCellMessage()));
					cell.setCellComment(comment);
				}else{
					cell = createCell(row, k, whiteStyle);
				}
				cell.setCellValue(String.valueOf(fieldValue));
			}
		}
		writeWorkbook(wb, filePath);
	}
	
	/**
	 * 根据行号和列号，判断给出的属性值是否有错误
	 * @param row
	 * @param cell
	 * @return
	 */
	private static boolean isFiledError(Map<String, ExcelImportError> errorMap,int row,int cell){
		boolean result = false;
		String temp = row + "-" + cell;
		if(errorMap.get(temp)!=null){
			result = true;
		}
		return result;
	}
	
	private static Map<String, ExcelImportError> getRowCellValue(List<ExcelImportError> errorList){
		Map<String, ExcelImportError> errorMap = new HashMap<String, ExcelImportError>();
		for(ExcelImportError error : errorList){
			errorMap.put(error.getRow()+"-"+error.getCell(), error);
		}
		return errorMap;
	}
}
