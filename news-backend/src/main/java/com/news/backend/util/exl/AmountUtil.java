package com.news.backend.util.exl;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.news.common.core.utils.BigDecimalUtil;



public class AmountUtil {
	
	/**
	 * 日志
	 */
	private static Logger _log = LoggerFactory.getLogger(AmountUtil.class);

	/**
	 * 将字符串的元转换为分
	 * 
	 * @param amount
	 * @return
	 */
	public static Long transYuanToFenWithString(String amount) {
		if(StringUtils.isNotBlank(amount)) {
			try {
				BigDecimal bg = new BigDecimal(amount);
				bg = bg.multiply(new BigDecimal(100));
				return bg.longValue();
			} catch (Exception e) {
				_log.error("转换字符金额失败：" + amount, e);
				throw new IllegalArgumentException(e);
			}
		}
		return null ;
	}
	/**
	 * 按百分比(%)计算金额（金额是分）
	 * @param amt
	 * @param percent
	 * @return
	 */
	public static long toAmtByPercent(long amt ,long percent)
	{
		BigDecimal a =BigDecimal.valueOf(amt);
		BigDecimal b =new BigDecimal(percent).divide(new BigDecimal(10000));
		return a.multiply(b).longValue();
	}
	private static boolean isBlank(String value) {
		if (value == null || "".equals(value.trim()) || "null".equals(value.trim()))
			return true;
		return false;
	}
	
	public static String formatMoney(String money) {
		if (isBlank(money))
			return "0.00";
		try {
			Long m = Long.parseLong(money);
			return BigDecimalUtil.formatMoney(m);
		} catch (Exception e) {
			_log.error("转换金额失败,money=" + money, e);
		}
		return "";
	}
}
