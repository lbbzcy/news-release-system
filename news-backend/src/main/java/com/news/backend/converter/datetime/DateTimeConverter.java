package com.news.backend.converter.datetime;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import com.news.backend.converter.datetime.DateTimeParser;
import com.news.backend.converter.datetime.impl.YDateTimeParser;
import com.news.backend.converter.datetime.impl.YMDDateTimeParser;
import com.news.backend.converter.datetime.impl.YMDHDateTimeParser;
import com.news.backend.converter.datetime.impl.YMDHMDateTimeParser;
import com.news.backend.converter.datetime.impl.YMDHMSDateTimeParser;
import com.news.backend.converter.datetime.impl.YMDateTimeParser;

public class DateTimeConverter extends PropertyEditorSupport {
	private final DateTimeParser dateTimeParser = new YMDHMSDateTimeParser(new YMDDateTimeParser(
			new YMDHMDateTimeParser(new YMDHDateTimeParser(new YMDateTimeParser(new YDateTimeParser())))));
	private String pattern = "yyyy-MM-dd HH:mm:ss";
	private ThreadLocal<DateFormat> dateformat = new ThreadLocal<DateFormat>() {
		protected DateFormat initialValue() {
			return new SimpleDateFormat(DateTimeConverter.this.pattern);
		}
	};

	public DateTimeConverter() {
	}

	public DateTimeConverter(String pattern) {
		if (StringUtils.isNotBlank(pattern)) {
			this.pattern = pattern;
		}

	}

	public String getAsText() {
		return this.getValue() != null ? ((DateFormat) this.dateformat.get()).format(this.getValue()) : null;
	}

	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isBlank(text)) {
			this.setValue((Object) null);
		} else {
			this.setValue(this.dateTimeParser.parse(text));
		}

	}
}
