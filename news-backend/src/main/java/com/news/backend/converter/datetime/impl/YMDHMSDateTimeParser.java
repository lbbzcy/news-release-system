package com.news.backend.converter.datetime.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.news.backend.converter.datetime.AbstractDateTimeParser;

public class YMDHMSDateTimeParser extends AbstractDateTimeParser {

	private static Logger _log = LoggerFactory.getLogger(YMDHMSDateTimeParser.class);
	private ThreadLocal<DateFormat> dateFormatThreadLocal = new ThreadLocal<DateFormat>() {
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	public YMDHMSDateTimeParser() {
	}

	public YMDHMSDateTimeParser(AbstractDateTimeParser successor) {
		super(successor);
	}

	public Date parse0(String str) throws ParseException {
		try {
			return ((DateFormat) this.dateFormatThreadLocal.get()).parse(str);
		} catch (ParseException arg2) {
			_log.error("YMDHMSDatetimeParser(yyyy-MM-dd HH:mm:ss) parse time error : " + str);
			throw arg2;
		}
	}

}
