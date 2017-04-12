package com.news.backend.system.context;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ParameterRequestWrapper extends HttpServletRequestWrapper {
	private Map<String, String[]> params = new HashMap<String, String[]>();

	@SuppressWarnings("unchecked")
	public ParameterRequestWrapper(HttpServletRequest request) {
		super(request);
		// 将参数表，赋予给当前的Map以便于持有request中的参数
		this.params.putAll(request.getParameterMap());
	}

	@Override
	public String getParameter(String name) {// 重写getParameter，代表参数从当前类中的map获取
		String[] values = params.get(name);
		if (values == null || values.length == 0) {
			return null;
		}
		return values[0];
	}

	@Override
	public String[] getParameterValues(String name) {// 同上
		return params.get(name);
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		return params;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return Collections.enumeration(params.keySet());
	}

	public void addParameter(String name, Object value) {// 增加参数
		if (value != null) {
			if (value instanceof String[]) {
				params.put(name, (String[]) value);
			} else if (value instanceof String) {
				params.put(name, new String[] { (String) value });
			} else {
				params.put(name, new String[] { String.valueOf(value) });
			}
		}
	}
}
