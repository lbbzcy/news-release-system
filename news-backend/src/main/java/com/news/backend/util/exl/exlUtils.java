package com.news.backend.util.exl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
/**
 * exl导入工具类
 * @author pst18
 *
 */
public class exlUtils {
	/**
	 * exl导入，获取参数
	 * @param cell
	 * @return
	 */
	public static String getStringCellValue(Cell cell) {
		if (cell == null) {
            return "";
        }
        String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf(cell.getNumericCellValue());
            int indexOf = strCell.indexOf(".");
            if(indexOf!=-1){
            	strCell=strCell.substring(0,indexOf);
            }
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        return strCell;
    }
}
