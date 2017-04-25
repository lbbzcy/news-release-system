<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>



<c:set var="SERVER" value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}" />
<c:set var="basepath" value="${SERVER}${pageContext.request.contextPath}" />
<c:set var="imagepath" value="${basepath}"/>
<c:set var="bannersrc" value="http://192.168.123.180/images"/>
