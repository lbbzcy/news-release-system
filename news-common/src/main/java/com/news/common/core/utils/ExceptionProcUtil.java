package com.news.common.core.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
/**
 * 异常处理工具类
 * @author lhui
 * @date 2016-10-22
 */
public class ExceptionProcUtil {
	/**
	 * 处理异常描述
	 * @param ex
	 * @return
	 */
	public static String getExceptionDesc(Exception ex) {
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			// 将出错的栈信息输出到printWriter中
			ex.printStackTrace(pw);
			pw.flush();
			sw.flush();
		} finally {
			if (sw != null) {
				try {
					sw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (pw != null) {
				pw.close();
			}
		}
		return sw != null ? sw.toString() : "";
	}
}
