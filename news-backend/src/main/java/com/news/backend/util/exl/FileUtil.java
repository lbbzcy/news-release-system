package com.news.backend.util.exl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.news.common.core.exception.ApplicationException;
import com.news.common.core.constant.BaseEnum;
import com.news.common.core.utils.BigDecimalUtil;
import com.news.common.core.utils.ExceptionProcUtil;
import com.news.common.project.exception.CheckedException;

public class FileUtil {
	/**导出excel文件每个工作簿(sheet)装填多少条数据*/
	protected static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	public static final int EXPORT_EXCEL_PAGESIZE =60000;
	public final static String DATE_FORMAT_yyyyMMdd= "yyyyMMdd";
	public final static String LONG_FORMAT_fenToYuan= "fenToYuan";
	public final static String INT_FORMAT_fenToYuan= "fenToYuan";
	public static void main(String[] args) {
		//String dirName = "d:/FH/topic/";// 创建目录
		//FileUtil.createDir(dirName);
		System.out.println(!Pattern.compile(RegexEnum.REGEX_POST.getRegexValue()).matcher("1")
				.matches());
	}

	/**
	 * 创建目录
	 * 
	 * @param destDirName
	 *            目标目录名
	 * @return 目录创建成功返回true，否则返回false
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建单个目录
		if (dir.mkdirs()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			myDelFile.delete();

		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			logger.error("删除文件操作出错，异常原因：{}",ExceptionProcUtil.getExceptionDesc(e));

		}

	}

	/**
	 * 读取到字节数组1
	 * 
	 * @param filePath
	 *            //路径
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static byte[] getContent(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length
				&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		fi.close();
		return buffer;
	}

	/**
	 * 读取到字节数组2
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(String filePath) throws IOException {

		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			logger.error("系统异常，异常原因：{}",ExceptionProcUtil.getExceptionDesc(e));
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				logger.error("系统异常，异常原因：{}",ExceptionProcUtil.getExceptionDesc(e));
			}
			bos.close();
		}
	}

	/**
	 * 读取到字节数组3
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray2(String filePath) throws IOException {

		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}

		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
				// System.out.println("reading");
			}
			return byteBuffer.array();
		} catch (IOException e) {
			logger.error("系统异常，异常原因：{}",ExceptionProcUtil.getExceptionDesc(e));
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				logger.error("系统异常，异常原因：{}",ExceptionProcUtil.getExceptionDesc(e));
			}
			try {
				fs.close();
			} catch (IOException e) {
				logger.error("系统异常，异常原因：{}",ExceptionProcUtil.getExceptionDesc(e));
			}
		}
	}

	/**
	 * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static byte[] toByteArray3(String filePath) throws IOException {

		FileChannel fc = null;
		try {
			fc = new RandomAccessFile(filePath, "r").getChannel();
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,
					fc.size()).load();
			// System.out.println(byteBuffer.isLoaded());
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				// System.out.println("remain");
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (IOException e) {
			logger.error("系统异常，异常原因：{}",ExceptionProcUtil.getExceptionDesc(e));
			throw e;
		} finally {
			try {
				fc.close();
			} catch (IOException e) {
				logger.error("系统异常，异常原因：{}",ExceptionProcUtil.getExceptionDesc(e));
			}
		}
	}

	/**
	 * 根据文件的后缀，获取Workbook Object（由于Office2003和Office2007、2010的处理方式不一样）
	 * 
	 * @param filePath
	 * @return Workbook
	 * @throws Exception
	 */
	public static Workbook getWorkBook(String filePath)
			throws ApplicationException {
		boolean isExcel2003 = true;// 是否是Excel 2003
		Workbook wb = null;
		String houzhui = filePath.substring(filePath.lastIndexOf("."),
				filePath.length());
		if (".xls".equals(houzhui) || ".xlsx".equals(houzhui)) {
			if (".xlsx".equals(houzhui)) {
				isExcel2003 = false;
			}
		} else {
			throw new ApplicationException("只支持xls或者xlsx文件格式的导入");
		}
		try {
			File file = new File(filePath);
			if (file == null || !file.exists()) {
				throw new ApplicationException("该文件不存在");
			}
			FileInputStream fis = new FileInputStream(file);
			wb = (Workbook) (isExcel2003 ? new HSSFWorkbook(fis)
					: new XSSFWorkbook(fis));
		} catch (Exception ex) {
			throw new ApplicationException("获取Workbook对象出现异常:请不要强转文件");
		}
		return wb;
	}

	/**
	 * 上传文件到临时目录
	 * 
	 * @param file
	 * @return
	 */
	public static String upLoadFile(MultipartFile file,String temp) {
		String filePath = temp + File.separator;
		File files = new File(filePath);
		if (!files.exists()) {// 如果该目录名称不存在，则创建他
			files.mkdirs();
		}
		// 获得上传的文件
		filePath = filePath + new Date().getTime() + file.getOriginalFilename();
		// 上传文件到指定目录
		try {
			file.transferTo(new File(filePath));
		} catch (Exception e) {
			logger.error("文件上传异常，异常原因：{}",ExceptionProcUtil.getExceptionDesc(e));
			throw new ApplicationException("文件上传异常");
		}
		return filePath;
	}

	/**
	 * 删除临时文件
	 * 
	 * @param filePath
	 */
	public static void delTempFile(String filePath) {
		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}

	/**
	 * 验证数据
	 * 
	 * @param cellName
	 * @param cellValue
	 * @param cellPosition
	 * @param regexEnum
	 * @return
	 */
	public static List<ExcelImportError> checkData(String cellName,
			String cellValue, int errorRow,int errorTd, RegexEnum regexEnum) {
		List<ExcelImportError> errorList = new ArrayList<ExcelImportError>();
		// 验证
		if (!Pattern.compile(regexEnum.getRegexValue()).matcher(cellValue)
				.matches()) {
			ExcelImportError error = new ExcelImportError();
			// 错误的列名
			error.setCellName(cellName);
			// 错误列所对应的属性值
			error.setCellValue(cellValue);
			// 错误列所在位置（例如：[3A]第三行第一列）
			error.setCellPosition("第[" + errorRow + "]行[" + errorTd + "]列");
			// 错误原因
			error.setCellMessage(regexEnum.getErrorMessage());
			errorList.add(error);
		}
		return errorList;

	}
	
	public static List<ExcelImportError> checkData(String cellName,
			String cellValue, int cellPosition,String errorMsg) {
		List<ExcelImportError> errorList = new ArrayList<ExcelImportError>();
		ExcelImportError error = new ExcelImportError();
		// 错误的列名
		error.setCellName(cellName);
		// 错误列所对应的属性值
		error.setCellValue(cellValue);
		// 错误列所在位置（例如：[3A]第三行第一列）
		error.setCellPosition("第[" + cellPosition + "]行");
		// 错误原因
		error.setCellMessage(errorMsg);
		errorList.add(error);
		return errorList;

	}
	
	public static String[] vailStringArray(String remark,String source,int num,int len,String splitChar) throws CheckedException
	{
		if(source==null)
			throw new  CheckedException("第[" + num + "]行\""+remark+"\"不能为空");
		String[] ret=source.split(splitChar);
		if(ret.length<len)
			throw new  CheckedException("第[" + num + "]行\""+remark+"\"数组元素个数不足");
		return ret;
	}
	
	
	public static List<ExcelImportError> endSettleDayError(String cellName,
			String cellValue, int cellPosition, String message) {
		List<ExcelImportError> errorList = new ArrayList<ExcelImportError>();
		// 验证
		ExcelImportError error = new ExcelImportError();
		// 错误的列名
		error.setCellName(cellName);
		// 错误列所对应的属性值
		error.setCellValue(cellValue);
		// 错误列所在位置（例如：[3A]第三行第一列）
		error.setCellPosition("第[" + cellPosition + "]行");
		// 错误原因
		error.setCellMessage(message);
		errorList.add(error);
		return errorList;
	}
	/** 
	 * 判断文件的编码格式 
	 * @param fileName :file 
	 * @return 文件编码格式 
	 * @throws Exception 
	 */  
	@SuppressWarnings("resource")
	public static String codeString(String fileName) throws Exception{  
	    BufferedInputStream bin = new BufferedInputStream(  
	    new FileInputStream(fileName));  
	    int p = (bin.read() << 8) + bin.read();  
	    String code = null;  
	      
	    switch (p) {  
	        case 0xefbb:  
	            code = "UTF-8";  
	            break;  
	        case 0xfffe:  
	            code = "Unicode";  
	            break;  
	        case 0xfeff:  
	            code = "UTF-16BE";  
	            break;  
	        default:  
	            code = "GBK";  
	    }  
	      
	    return code;  
	} 
	/****
	 * 
	 * @description 
	 * @param response
	 * @param subject
	 * @param title
	 * @param dataList枚举需要继承BaseEnum
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException  
	 * @author lyg
	 * @date 2016-8-23
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public static void toExcel(HttpServletResponse response,String subject, Map<String,String> title,List<?> dataList,Map<String, String> formatMap) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
			if(formatMap==null)
			{
				formatMap=new HashMap<String, String>();
			}
		  Set<String> keySet = title.keySet();
		  HSSFWorkbook workbook = new HSSFWorkbook();
		  HSSFSheet sheet = null;
		  //创建公共表头样式
	      HSSFCellStyle titleStyle = workbook.createCellStyle();
	      titleStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
	      titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	      titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      //创建公共表头字体
	      HSSFFont font = workbook.createFont();
	      font.setColor(HSSFColor.VIOLET.index);
	      font.setFontHeightInPoints((short) 12);
	      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	      // 把表头字体应用到表头样式中
	      titleStyle.setFont(font);

		  //创建信息行样式
	      HSSFCellStyle rowStyle = workbook.createCellStyle();
	      rowStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
	      rowStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	      rowStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	      rowStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	      rowStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	      rowStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	      rowStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	      rowStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	      //创建信息字体
	      HSSFFont rowFont = workbook.createFont();
	      rowFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
	      rowStyle.setFont(rowFont);
	      int totalSheet = (dataList.size() - 1)/EXPORT_EXCEL_PAGESIZE + 1;
	      int total = dataList.size();
	      
	      HSSFRow titleRow = null;
		  HSSFRow dtoRow = null;
		  HSSFCell dtoCell = null;
	      //如果没有数据只导出表头信息
	      if(total == 0){
		    	  sheet = workbook.createSheet(subject);
				  sheet.setDefaultColumnWidth(20);
			      //创建Excel表格第一行即表头信息
			      titleRow = sheet.createRow(0);
			      //填充excel表头信息  额外增加序号表头信息
			      HSSFCell cell = titleRow.createCell(0);
		          cell.setCellStyle(titleStyle);
		          cell.setCellValue("序号");
			      
			      int count = 1;
				  for(String key : keySet){
					  cell = titleRow.createCell(count);
			          cell.setCellStyle(titleStyle);
			          HSSFRichTextString text = new HSSFRichTextString(title.get(key));
			          cell.setCellValue(text);
			          count++;
				  }
	      }else{
				  Object obj = dataList.get(0);
				  Class<?> clazz = obj.getClass();
				  List<Class<?>> superCList = new ArrayList<Class<?>>();
				  //List<Field> fieldList = getCorrelativeFields(clazz,getSuperClasses(clazz,superCList),keySet);
				 // List<Method> methodList = getCorrelativeMethods(clazz,superCList,keySet);		
				  int startIndex = 0;
				  int endIndex = 0;
				  
				  // 创建每个sheet组装表头及取出数据填充每个工作簿
				  for(int currentSheet = 1;currentSheet <= totalSheet;currentSheet++){
						  sheet = workbook.createSheet(subject + currentSheet);
						  sheet.setDefaultColumnWidth(20);
					      //创建Excel表格第一行即表头信息
					      titleRow = sheet.createRow(0);
					      //填充excel表头信息  额外增加序号表头信息
					      HSSFCell cell = titleRow.createCell(0);
				          cell.setCellStyle(titleStyle);
				          cell.setCellValue("序号");
					      
					      int count = 1;
						  for(String key : keySet){
							  cell = titleRow.createCell(count);
					          cell.setCellStyle(titleStyle);
					          HSSFRichTextString text = new HSSFRichTextString(title.get(key));
					          cell.setCellValue(text);
					          count++;
						  }
						  startIndex = (currentSheet - 1) * EXPORT_EXCEL_PAGESIZE;
						  endIndex = (total >= currentSheet * EXPORT_EXCEL_PAGESIZE ? currentSheet * EXPORT_EXCEL_PAGESIZE - 1 : total -1);
						  //循环填充每个工作簿
						  int loop = 1;
						  for(int line = startIndex;line <= endIndex;line++){
							  dtoRow = sheet.createRow(loop);
							  Object object = dataList.get(line);
							  //添加序号字段
							  dtoCell = dtoRow.createCell(0);
							  dtoCell.setCellStyle(rowStyle);
							  HSSFRichTextString serial = new HSSFRichTextString(Integer.toString(line + 1));
							  dtoCell.setCellValue(serial);
							  try {
								  int j=0;
								  for(String keyValue:keySet){
										 dtoCell = dtoRow.createCell(j + 1);
										 dtoCell.setCellStyle(rowStyle);	 
										 Method method=clazz.getMethod("get"+keyValue.substring(0, 1).toUpperCase()+keyValue.substring(1).trim());
										 Object value = method.invoke(object, new Object[]{});
										 //其它类型可以按不同方式处理如图像等
										 if (value instanceof Integer) {
											 if(formatMap.get(keyValue)!=null)
					                    	 {
					                    		 dtoCell.setCellValue(BigDecimalUtil.parseMoneyWith2Point(Long.valueOf((Integer)value)));
					                    	 }
					                    	 else {
					                    		 dtoCell.setCellValue((Integer)value);
											}
					                     }else if(value instanceof Long) {
					                    	 if(formatMap.get(keyValue)!=null)
					                    	 {
					                    		 dtoCell.setCellValue(BigDecimalUtil.parseMoneyWith2Point((Long)value));
					                    	 }
					                    	 else {
					                    		 dtoCell.setCellValue((Long)value);
											}
					                     }else if(value instanceof Float) {
					                         dtoCell.setCellValue(new HSSFRichTextString( String.valueOf((Float) value)));
					                     }else if (value instanceof Double) {
					                    	 dtoCell.setCellValue(new HSSFRichTextString( String.valueOf((Double) value)));
					                     }else if (value instanceof Date) {
					                    	 if(formatMap.get(keyValue)!=null)
					                    	 {
					                    		 dtoCell.setCellValue(new SimpleDateFormat(formatMap.get(keyValue)).format((Date) value));
					                    	 }
					                    	 else {
					                    		 dtoCell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) value));
											}
					                     }else if (value instanceof BaseEnum) {
					                    	 dtoCell.setCellValue(new HSSFRichTextString(((BaseEnum) value).getDisplayName()));
					                     }else{
					                    	 dtoCell.setCellValue(value == null ? "" : value.toString());
					                     }
										 j++;
							     }
							} catch (Exception e) {
								logger.error("系统异常，异常原因：{}",ExceptionProcUtil.getExceptionDesc(e));
							}
							 
							 loop++;
						  }
				     }
	      }
	      OutputStream output = response.getOutputStream();
		  response.reset();  
		  response.setHeader("Content-Disposition", "attachment; filename=" + toUTF8(subject) + ".xls");  
		  response.setContentType("application/msexcel"); 
		  response.setCharacterEncoding("charset=utf-8"); 
		  workbook.write(output);
	      output.close();
			  
	}
	/***
	 * 
	 * @description 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException  
	 * @author lyg
	 * @date 2016-8-23
	 */
	public static String toUTF8(String str) throws UnsupportedEncodingException { 
	     StringBuffer sb = new StringBuffer(); 
	     for(int i = 0;i < str.length();i++){
	    	 char c = str.charAt(i);
	    	 if  (c >= 0 && c <= 255){
	    		   sb.append(c);
	         }else {
	             byte [] b = Character.toString(c).getBytes("utf-8");
		         for(int j = 0;j < b.length;j++) {
	        	   int k = b[j];
	        	   if (k < 0) k += 256; 
	        	   sb.append("%" + Integer.toHexString(k).toUpperCase()); 
		         }
	         }
	     }
	     return sb.toString();
	}
	
	
}