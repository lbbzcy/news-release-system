<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%-- <%@ taglib uri="http://www.longtimeit.com/taglib-elfunctions" prefix="ltfun" %>
<%@ taglib uri="http://www.longtimeit.com/taglib-sns" prefix="sns" %> --%>



<c:set var="SERVER" value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}" />
<c:set var="basepath" value="${SERVER}${pageContext.request.contextPath}" />
<c:set var="path" value="${SERVER}${pageContext.request.contextPath}" />
<c:set var="requestURL" value="${pageContext.request.requestURL}" />
<c:set var="currentUid" value="${locuser.id}" />
<c:set var="currentUname" value="${locuser.name}" />
<c:set var="currentUmobile" value="${locuser.mobile}" />
<c:set var="islogin" value="${islogin}" />
<c:set var="imagepath" value="${basepath}"/>
<c:set var="bannersrc" value="http://192.168.123.180/images"/>
