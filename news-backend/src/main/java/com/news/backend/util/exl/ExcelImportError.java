package com.news.backend.util.exl;


/**
 * 保存Excel导入验证的错误信息
 * @author yu 2013-01-31
 *
 */
public class ExcelImportError {
	private String cellName;			//错误的列名
	private String cellValue;			//错误列所对应的属性值
	private String cellPosition;		//错误列所在位置（例如：[3A]第三行第一列）
	private String cellMessage;			//错误原因
	private int row;					//错误所在行
	private int cell;					//错误所在列
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public String getCellValue() {
		return cellValue;
	}
	public void setCellValue(String cellValue) {
		this.cellValue = cellValue;
	}
	public String getCellPosition() {
		return cellPosition;
	}
	public void setCellPosition(String cellPosition) {
		this.cellPosition = cellPosition;
	}
	public String getCellMessage() {
		return cellMessage;
	}
	public void setCellMessage(String cellMessage) {
		this.cellMessage = cellMessage;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCell() {
		return cell;
	}
	public void setCell(int cell) {
		this.cell = cell;
	}
}
