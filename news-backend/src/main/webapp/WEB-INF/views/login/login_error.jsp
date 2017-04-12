<%@page language="java" pageEncoding ="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@page import="com.news.backend.system.shiro.exception.*"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="org.apache.shiro.authc.*"%>
<%@include file="../include/taglibs.jsp"%>
<%
	Object obj=request.getAttribute(org.apache.shiro.web.filter.authc.FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	AuthenticationException authExp = (AuthenticationException)obj;
	if( authExp != null ){
		String expMsg="";
		if(authExp instanceof UnknownAccountException || authExp instanceof IncorrectCredentialsException || authExp instanceof AuthenticationException){
			expMsg="账号或密码错误！";
		} else if( obj instanceof UserNotFoundException ){
			expMsg= "账号或密码错误！";
		} else{
			expMsg="登录异常" ;
		}
		out.print(expMsg);
	}
%>