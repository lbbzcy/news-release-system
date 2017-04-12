package com.news.backend.converter;

import java.util.Date;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import com.news.backend.converter.datetime.DateTimeConverter;

public class DatePropertyEditorBinder implements PropertyEditorRegistrar {

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(Date.class, new DateTimeConverter("yyyy-MM-dd HH:mm:ss"));
	}

}
