package com.news.common.core.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;

/**
 * @author LS
 * @date 2014-1-16
 * @description：TODO
 */

public class BigDecimalUtil {

	/**
	 * 将分转换为元
	 * 
	 * @description
	 * @param money
	 * @return
	 * @author LS
	 * @date 2014-1-16
	 */
	public static String parseMoneyWith2Point(Long money) {
		if (money == null)
			return null;
		BigDecimal d1 = new BigDecimal(money);
		return d1.divide(new BigDecimal(100)).toString();
	}

	/**
	 * 将money格式化#,##0.00
	 * 
	 * @description
	 * @return
	 * @author LS
	 * @date 2014-1-16
	 */
	public static String formatMoney(Long money) {
		if (money == null)
			return "0.00";
		return new DecimalFormat("#,##0.00").format(new BigDecimal(parseMoneyWith2Point(money)));
	}
	/**
	 * 将money格式化###0.00
	 * 
	 * @description
	 * @return
	 * @author LS
	 * @date 2014-1-16
	 */
	public static String formatMoney2(Long money) {
		if (money == null)
			return "0.00";
		return new DecimalFormat("#0.00").format(new BigDecimal(parseMoneyWith2Point(money)));
	}
	/**
	 * 保留两位小数格式化#0.00
	 * 
	 * @description
	 * @return
	 * @author LS
	 * @date 2014-1-16
	 */
	public static String formatDouble(double  vlaue) {
		if (vlaue == 0.0)
			return "0.00";
		return new DecimalFormat("#0.00").format(vlaue);
	}
	/**
	 * 保留三位小数格式化#0.00
	 * 
	 * @description
	 * @return
	 * @author LS
	 * @date 2014-1-16
	 */
	public static String format3point(Integer  vlaue) {
		if (vlaue == null|| vlaue == 0)
			return "";
		BigDecimal bg = tran3point(String.valueOf(vlaue));
		if(bg == null){
			return "";
		}
		return bg.toString();
	}
	public static BigDecimal tran3point(String vlaue) {
		if(StringUtils.isNotBlank(vlaue)) {
			try {
				BigDecimal bg = new BigDecimal(vlaue);
				bg = bg.divide(new BigDecimal(1000));
				return bg;
			} catch (Exception e) {
				throw new IllegalArgumentException(e);
			}
		}
		return null ;
	}
	public static void main(String[] args) {
		System.out.println(formatMoney2(112255334431L));
	}
}
