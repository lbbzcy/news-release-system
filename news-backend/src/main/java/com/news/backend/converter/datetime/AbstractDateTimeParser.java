package com.news.backend.converter.datetime;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDateTimeParser implements DateTimeParser {

	private static Logger _log = LoggerFactory.getLogger(AbstractDateTimeParser.class);
	private AbstractDateTimeParser successor;

	public AbstractDateTimeParser() {
	}

	public AbstractDateTimeParser(AbstractDateTimeParser successor) {
		this.successor = successor;
	}

	public Date parse(String str) {
		if (StringUtils.isNotBlank(str)) {
			try {
				return this.parse0(str);
			} catch (ParseException arg2) {
				if (this.successor != null) {
					return this.successor.parse(str);
				}
			}
		}

		_log.warn("date format error : date str is null or \'\' ");
		return null;
	}

	public abstract Date parse0(String arg0) throws ParseException;

}
