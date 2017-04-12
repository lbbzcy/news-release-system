package com.news.backend.converter;

import java.util.Date;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.news.backend.converter.datetime.DateTimeConverter;

public class DateWebBindingInitializer implements WebBindingInitializer {

	private String datePattern = "yyyy-MM-dd HH:mm:ss";

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new DateTimeConverter(this.datePattern));
	}
}
