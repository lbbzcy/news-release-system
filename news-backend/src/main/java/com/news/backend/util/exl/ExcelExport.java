package com.news.backend.util.exl;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.news.common.core.utils.ReflectionUtil;
import com.news.common.core.constant.BaseEnum;
import com.news.common.core.utils.ExceptionProcUtil;


/**
 * Excel 导出工具类
 * 
 * @author yu
 * @date 2015-05-25
 */
public class ExcelExport {
	/**
	 * 日志记录器
	 */
	private static Logger log = LoggerFactory.getLogger(ExcelExport.class);
	
	/**
     * 导出Excel文件到指定的目录
     * 
     * @param modelName 在ExcelExoport-mapping对应的id名称，用来确认Excel列的标题和属性存放在Map的key值
     * @param dataList 数据集
     * @param filePath 例如E:\Excel\TEST.XLS
     */
    public static <T> void exportExcel(String modelName, List<T> dataList,
        String filePath)
    {
        HSSFWorkbook wb = createWorkbook(modelName, dataList);
        // 输出文件
        writeWorkbook(wb, filePath);
    }

	/**
	 * 导出Excel
	 * 
	 * @param <T>
	 * @param clazz
	 *            数据类
	 * @param modelName
	 *            在ExcelExoport-mapping对应的id名称，用来确认Excel列的标题和通过反射取属性值
	 * @param dataList 要导出的数据集合
	 * @param out 输出流
	 */
	public static <T> void exportExcel(String modelName,
			List<T> dataList, OutputStream out) {
		// 创建HSSFWorkbook
		HSSFWorkbook wb = createWorkbook(modelName, dataList);
		// 输出文件
		writeWorkbook(wb, out);
	}

	/**
	 * 将数据写入到Excel的工作薄
	 * 
	 * @param modelName
	 *            在ExcelExoport-mapping对应的id名称，用来确认Excel列的标题和属性存放在Map的key值
	 * @param dataList
	 *            数据集
	 * @param filePath
	 *            例如E:\Excel\TEST.XLS
	 * @return HSSFWorkbook
	 */
	public static <T> HSSFWorkbook createWorkbook(String modelName,
			List<T> dataList) {
		ExcelExportModel excelModel = ExcelConfigManager.getExcelExportModel(modelName);
		List<ExcelExportModelProperty> modeList = ExcelConfigManager
				.getExcelExportModelProperty(modelName);

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = createSheet(wb, excelModel.getSheetName());

		// 定义列标题字体
		Font columnFont = createFont(wb, HSSFFont.BOLDWEIGHT_BOLD,
				HSSFColor.BLACK.index, (short) 10);

		// 背景色为灰色
		CellStyle darkgrayStyle = createBorderCellStyle(wb,
				HSSFColor.GREY_25_PERCENT.index,
				HSSFColor.GREY_25_PERCENT.index, CellStyle.ALIGN_CENTER,
				columnFont);
		// 背景色为白色
		CellStyle whiteStyle = createBorderCellStyle(wb, HSSFColor.WHITE.index,
				HSSFColor.WHITE.index, CellStyle.ALIGN_CENTER);

		// 创建列标题栏
		initExcelColumnTitle(modeList, sheet, darkgrayStyle);
		// 加载数据列
		initExcelColumnData(modeList, dataList, wb, sheet, whiteStyle);
		// 自动调整列宽
		autoSizeColumn(modeList, sheet);
		return wb;
	}

	/**
	 * 初始Exel的列标题
	 * 
	 * @param modelName
	 * @param sheet
	 * @param cellStyle
	 */
	private static void initExcelColumnTitle(List<ExcelExportModelProperty> list,
			HSSFSheet sheet, CellStyle cellStyle) {
		HSSFRow row = createRow(sheet, 0);
		HSSFCell cell = createCell(row, 0, cellStyle);
		cell.setCellValue("序号");
		for (int i = 0, len = list.size(); i < len; i++) {
			ExcelExportModelProperty model = list.get(i);
			cell = createCell(row, i + 1, cellStyle);
			cell.setCellValue(model.getExcelTitleName());
		}
	}

	/**
	 * 初始话导出的Excel标题
	 * 
	 * @param excelModel
	 * @param sheet
	 * @param cellStyle
	 */
	@SuppressWarnings("unused")
	private static void initExcelTitle(ExcelExportModel excelModel, HSSFSheet sheet,CellStyle cellStyle) {
		// 获取Excel导出配置信息
		HSSFRow row = createRow(sheet, 0);
		row.setHeight((short) 1000);
		HSSFCell cell = createCell(row, 0, cellStyle);
		cell.setCellValue(excelModel.getTitle());
	}

	/**
	 * 初始话Excel列的数据
	 * 
	 * @param modelName
	 * @param dataList
	 * @param sheet
	 * @param cellStyle
	 */
	private static <T> void initExcelColumnData(
			List<ExcelExportModelProperty> modelList, List<T> dataList,
			HSSFWorkbook wb, HSSFSheet sheet, CellStyle defaultCellStyle) {

		Map<String, CellStyle> cellStyleMap = initColumnDataStyle(wb);

		// 加载数据到Excel工作簿
		for (int i = 0, len = dataList.size(); i < len; i++) {
			HSSFRow row = createRow(sheet, i + 1);
			HSSFCell cell = createCell(row, 0, defaultCellStyle);
			cell.setCellValue(i + 1);
			T t = dataList.get(i);//获取数据集
			for (int j = 0; j < modelList.size(); j++) {
				ExcelExportModelProperty model = modelList.get(j);
				// 获取该单元格样式
				CellStyle cellStyle = getCellStyle(model, cellStyleMap);
				// 创建新的单元格
				cell = createCell(row, j + 1, cellStyle);
				// 为单元格添加内容
				setCellValue(cell, model, getValue(t, model.getName()));
				
			}
		}
	}
	
	/**
	 * 通过反射获取某个对象的属性值
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	private static Object getValue(Object obj,String fieldName){
		if(fieldName.indexOf(".")!=-1){
			String[] fieldNameArr = fieldName.split("\\.");
			for(String name : fieldNameArr){	
				if(obj == null){
					return obj;
				}
				obj = ReflectionUtil.reflectGetValue(obj,name);				
			}
		}else{
			obj = ReflectionUtil.reflectGetValue(obj,fieldName);
		}
		return obj;
	}

	/**
	 * 初始话本次Excel数据加载所需单元格样式
	 * 
	 * @param wb
	 * @return
	 */
	private static Map<String, CellStyle> initColumnDataStyle(HSSFWorkbook wb) {
		Map<String, CellStyle> cellStyleMap = new HashMap<String, CellStyle>();
		// 创建左对齐的样式
		CellStyle leftCellStyle = createBorderCellStyle(wb,
				HSSFColor.WHITE.index, HSSFColor.WHITE.index,
				CellStyle.ALIGN_LEFT);
		// 创建左对齐且内容超出列宽自动换行的样式
		CellStyle leftAndWrapCellStyle = createBorderCellStyle(wb,
				HSSFColor.WHITE.index, HSSFColor.WHITE.index,
				CellStyle.ALIGN_LEFT, true);

		// 创建右对齐的样式
		CellStyle rightCellStyle = createBorderCellStyle(wb,
				HSSFColor.WHITE.index, HSSFColor.WHITE.index,
				CellStyle.ALIGN_RIGHT);
		// 创建右对齐且内容超出列宽自动换行的样式
		CellStyle rightAndWrapCellStyle = createBorderCellStyle(wb,
				HSSFColor.WHITE.index, HSSFColor.WHITE.index,
				CellStyle.ALIGN_RIGHT, true);

		// 创建居中对齐的样式
		CellStyle centerCellStyle = createBorderCellStyle(wb,
				HSSFColor.WHITE.index, HSSFColor.WHITE.index,
				CellStyle.ALIGN_CENTER);
		// 创建居中对齐且内容超出列宽自动换行的样式
		CellStyle centerAndWrapCellStyle = createBorderCellStyle(wb,
				HSSFColor.WHITE.index, HSSFColor.WHITE.index,
				CellStyle.ALIGN_CENTER);

		cellStyleMap.put("ALIGN_LEFT", leftCellStyle);
		cellStyleMap.put("ALIGN_LEFT_WRAP", leftAndWrapCellStyle);

		cellStyleMap.put("ALIGN_RIGHT", rightCellStyle);
		cellStyleMap.put("ALIGN_RIGHT_WRAP", rightAndWrapCellStyle);

		cellStyleMap.put("ALIGN_CENTER", centerCellStyle);
		cellStyleMap.put("ALIGN_CENTER_WRAP", centerAndWrapCellStyle);
		return cellStyleMap;
	}

	/**
	 * 根据XML配置的属性，获取相应的单元格样式
	 * 
	 * @param model
	 *            ExcelModelProperty
	 * @param cellStyleMap
	 *            Map<String, CellStyle>
	 * @return
	 */
	private static CellStyle getCellStyle(ExcelExportModelProperty model,
			Map<String, CellStyle> cellStyleMap) {
		CellStyle cellStyle = null;
		if (model.isAutoLine()) {
			if ("left".equalsIgnoreCase(model.getAlign())) {
				cellStyle = cellStyleMap.get("ALIGN_LEFT_WRAP");
			} else if ("right".equalsIgnoreCase(model.getAlign())) {
				cellStyle = cellStyleMap.get("ALIGN_RIGHT_WRAP");
			} else {
				cellStyle = cellStyleMap.get("ALIGN_CENTER_WRAP");
			}
		} else {
			if ("left".equalsIgnoreCase(model.getAlign())) {
				cellStyle = cellStyleMap.get("ALIGN_LEFT");
			} else if ("right".equalsIgnoreCase(model.getAlign())) {
				cellStyle = cellStyleMap.get("ALIGN_RIGHT");
			} else {
				cellStyle = cellStyleMap.get("ALIGN_CENTER");
			}
		}
		return cellStyle;
	}

	/**
	 * 为单元格设置值
	 * 
	 * @param cell
	 *            HSSFCell
	 * @param model
	 *            ExcelModelProperty
	 * @param data
	 *            Object
	 */
	private static void setCellValue(HSSFCell cell, ExcelExportModelProperty model,
			Object data) {
		if (data == null) {
			data = model.getDefaultValue();
		}
		if ("int".equalsIgnoreCase(model.getDataType())
				|| "double".equalsIgnoreCase(model.getDataType())) {
			cell.setCellValue(Double.valueOf(String.valueOf(data)));
		} else if ("String".equalsIgnoreCase(model.getDataType())) {
			
			cell.setCellValue(String.valueOf(data));
		
		} else if ("Date".equalsIgnoreCase(model.getDataType())) {
			
			cell.getCellStyle().setDataFormat(
					HSSFDataFormat.getBuiltinFormat(model.getFormat()));
			cell.setCellValue(DateUtil.dateToStr((Date) data, model.getFormat()));
		
		}else if ("Long".equalsIgnoreCase(model.getDataType())) {
			
			cell.setCellValue(AmountUtil.transYuanToFenWithString(String.valueOf(data)));
		
		}else if("Enum".equalsIgnoreCase(model.getDataType())){
			if(data instanceof BaseEnum<?, ?>){
				BaseEnum<?, ?> enums = (BaseEnum<?,?>)data;
				cell.setCellValue(enums.getDisplayName());
			}else{
				log.info("枚举类型的数据必须实现BaseEnum");
			}
		}else {
			cell.setCellValue(new HSSFRichTextString(String.valueOf(data)));
		}
	}

	/**
	 * 自动调整列宽
	 * 
	 * @param modelName
	 * @param sheet
	 */
	public static void autoSizeColumn(List<ExcelExportModelProperty> modelList,
			HSSFSheet sheet) {
		sheet.setColumnWidth(0, (short) 3 * 256);

		for (int i = 0; i < modelList.size(); i++) {
			ExcelExportModelProperty model = modelList.get(i);
			if (model.getColumnWidth() > 0) {
				sheet.setColumnWidth(i + 1, model.getColumnWidth() * 256);
			} else {
				sheet.autoSizeColumn((short) i + 1);
			}
		}
	}

	/**
	 * 将HSSFWorkbook写入Excel文件
	 * 
	 * @param wb
	 *            HSSFWorkbook
	 * @param filePath
	 *            文件输出路径
	 */
	public static void writeWorkbook(HSSFWorkbook wb, String filePath) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
			wb.write(fos);
		} catch (FileNotFoundException e) {
			log.error(new StringBuffer("[").append(ExceptionProcUtil.getExceptionDesc(e)).append("]")
					.append(e.getCause()).toString());
		} catch (IOException e) {
			log.error(new StringBuffer("[").append(ExceptionProcUtil.getExceptionDesc(e)).append("]")
					.append(e.getCause()).toString());
		} finally {
			if (fos != null) {
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					log.error(new StringBuffer("[").append(ExceptionProcUtil.getExceptionDesc(e))
							.append("]").append(e.getCause()).toString());
				}
			}
		}
	}

	/**
	 * 将Excel文件写入到输出流
	 * 
	 * @param wb
	 *            HSSFWorkbook
	 * @param out
	 *            OutputStream
	 */
	public static void writeWorkbook(HSSFWorkbook wb, OutputStream out) {
		try {
			wb.write(out);
		} catch (IOException e) {
			log.error(new StringBuffer("[").append(ExceptionProcUtil.getExceptionDesc(e)).append("]")
					.append(e.getCause()).toString());
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					log.error(new StringBuffer("[").append(ExceptionProcUtil.getExceptionDesc(e))
							.append("]").append(e.getCause()).toString());
				}
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
	public static HSSFRow createRow(HSSFSheet sheet, int rowNum, int height) {
		HSSFRow row = sheet.createRow(rowNum);
		row.setHeight((short) height);
		return row;
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
	 * 功能：创建CellStyle样式
	 * 
	 * @param wb
	 *            HSSFWorkbook
	 * @param backgroundColor
	 *            背景色
	 * @param foregroundColor
	 *            前置色
	 * @param font
	 *            字体
	 * @return CellStyle
	 */
	public static CellStyle createCellStyle(HSSFWorkbook wb, short halign,
			Font font) {
		CellStyle cs = wb.createCellStyle();
		cs.setAlignment(halign);
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cs.setFont(font);
		return cs;
	}

	/**
	 * 功能：创建CellStyle样式
	 * 
	 * @param wb
	 *            HSSFWorkbook
	 * @param backgroundColor
	 *            背景色
	 * @param foregroundColor
	 *            前置色
	 * @param font
	 *            字体
	 * @return CellStyle
	 */
	public static CellStyle createCellStyle(HSSFWorkbook wb,
			short backgroundColor, short foregroundColor, short halign,
			Font font) {
		CellStyle cs = wb.createCellStyle();
		cs.setAlignment(halign);
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cs.setFillBackgroundColor(backgroundColor);
		cs.setFillForegroundColor(foregroundColor);
		cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cs.setFont(font);
		return cs;
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
	 * @param font
	 *            字体
	 * @return CellStyle
	 */
	public static CellStyle createBorderCellStyle(HSSFWorkbook wb,
			short backgroundColor, short foregroundColor, short halign,
			Font font) {
		CellStyle cs = wb.createCellStyle();
		cs.setAlignment(halign);
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cs.setFillBackgroundColor(backgroundColor);
		cs.setFillForegroundColor(foregroundColor);
		cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cs.setFont(font);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		return cs;
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
	 * @param halign
	 *            对齐方式
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
	 * 功能：创建带边框的CellStyle样式
	 * 
	 * @param wb
	 *            HSSFWorkbook
	 * @param backgroundColor
	 *            背景色
	 * @param foregroundColor
	 *            前置色
	 * @param halign
	 *            对齐方式
	 * @param wrapText
	 *            内容大于列宽是否自动换行
	 * @return CellStyle
	 */
	public static CellStyle createBorderCellStyle(HSSFWorkbook wb,
			short backgroundColor, short foregroundColor, short halign,
			boolean wrapText) {
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
		cs.setWrapText(wrapText);
		return cs;
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
	 * 功能：合并单元格
	 * 
	 * @param sheet
	 *            HSSFSheet
	 * @param firstRow
	 *            int
	 * @param lastRow
	 *            int
	 * @param firstColumn
	 *            int
	 * @param lastColumn
	 *            int
	 * @return int 合并区域号码
	 */
	public static int mergeCell(HSSFSheet sheet, int firstRow, int lastRow,
			int firstColumn, int lastColumn) {
		return sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow,
				firstColumn, lastColumn));
	}

	/**
	 * 功能：创建字体
	 * 
	 * @param wb
	 *            HSSFWorkbook
	 * @param boldweight
	 *            short
	 * @param color
	 *            short
	 * @return Font
	 */
	public static Font createFont(HSSFWorkbook wb, short boldweight,
			short color, short size) {
		Font font = wb.createFont();
		font.setBoldweight(boldweight);
		font.setColor(color);
		font.setFontHeightInPoints(size);
		return font;
	}

	/**
	 * 设置合并单元格的边框样式
	 * 
	 * @param sheet
	 *            HSSFSheet
	 * @param ca
	 *            CellRangAddress
	 * @param style
	 *            CellStyle
	 */
	public static void setRegionStyle(HSSFSheet sheet, CellRangeAddress ca,
			CellStyle style) {
		for (int i = ca.getFirstRow(); i <= ca.getLastRow(); i++) {
			HSSFRow row = HSSFCellUtil.getRow(i, sheet);
			for (int j = ca.getFirstColumn(); j <= ca.getLastColumn(); j++) {
				HSSFCell cell = HSSFCellUtil.getCell(row, j);
				cell.setCellStyle(style);
			}
		}
	}
}
