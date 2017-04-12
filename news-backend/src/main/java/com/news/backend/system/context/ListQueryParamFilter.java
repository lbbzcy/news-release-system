package com.news.backend.system.context;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ListQueryParamFilter implements Filter {

private static final String QUERY_PARAM_PREFIX = "QUERY_PARAM_PREFIX";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;

		//只处理列表方法请求
		String absoluteUri = getAbsoluteUri(httpRequest);
		if(null == absoluteUri || "".equals(absoluteUri)){
			chain.doFilter(httpRequest, response);
			return ;
		}
		
		//获取查询参数
		ParameterRequestWrapper parameterRequestWrapper = new ParameterRequestWrapper(httpRequest);
		Enumeration<?> enums = parameterRequestWrapper.getParameterNames();
		StringBuilder sb = new StringBuilder();
		
		while (enums.hasMoreElements()) {
			String key = (String) enums.nextElement();
			if("t".equals(key) || "menuclick".equals(key) || "uritype".equals(key) || 
					"validate_errros_model_obj".equals(key) || "validate_errros_count".equals(key)){
				continue;
			}
			sb.append("&" + key + "=" + request.getParameter(key));
		}
		
		String mc = httpRequest.getParameter("menuclick");
		if("1".equals(mc)){
			//有参数，则获取参数并存储参数
			String queryParam = "";
			if(sb.length() > 0){
				queryParam = new String(sb.toString().getBytes(), "UTF-8");
			}
			parameterRequestWrapper.getSession().setAttribute(getKey(parameterRequestWrapper, absoluteUri), queryParam);
			chain.doFilter(httpRequest, response);
			return;
		}
		
		if(sb.length() == 0){
			//没有查询参数，则获取查询参数
			String queryParam = (String)parameterRequestWrapper.getSession().getAttribute(getKey(parameterRequestWrapper, absoluteUri));
			if(null == queryParam || "".equals(queryParam)){
				chain.doFilter(parameterRequestWrapper, response);
				return ;
			}
			String [] kvs = queryParam.split("&");
			if(kvs.length > 0){
				for(String kv : kvs){
					String [] arr = kv.split("=");
					if(arr.length ==2 && !"".equals(arr[0])) {
						parameterRequestWrapper.addParameter(arr[0], arr[1]);
					}
				}
			}
		} else {
			//有参数，则获取参数并存储参数
			String queryParam = new String(sb.toString().getBytes(), "UTF-8");
			parameterRequestWrapper.getSession().setAttribute(getKey(parameterRequestWrapper, absoluteUri), queryParam);
		}
		chain.doFilter(parameterRequestWrapper, response);
	}
	
	private String getKey(HttpServletRequest request,String absoluteUri){
		String uriType = request.getParameter("uritype");
		if(uriType == null || "".equalsIgnoreCase(uriType.trim())){
			return QUERY_PARAM_PREFIX+absoluteUri;
		} else {
			return QUERY_PARAM_PREFIX+absoluteUri+"?uritype" + uriType;
		}
	}

	private String getAbsoluteUri(HttpServletRequest request) {
		String uri = request.getRequestURI(); 
		int idx = uri.lastIndexOf("/");
		String method = uri.substring(idx+1);
		if(!(method.startsWith("list") || 
				method.endsWith("list.html") || 
				method.endsWith("List.html")) || 
				uri.endsWith("/clear/checkinner/check_detail.html") ||
				uri.endsWith("/clear/checkchannel/adjust.html")){
			//只针对列表页面进行处理
			return null;
		}
		return uri;
	}
	
	@Override
	public void destroy() {

	}

}
