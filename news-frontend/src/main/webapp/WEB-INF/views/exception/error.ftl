<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit"> 
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>失败页面</title>
		<#include "/template/allJSAndCSS.ftl">
	</head>
	<body>
		<#if Session["session_user"]??>
			<div class="row bg-gray border-bottom">
				<div class="box">
					<div class="topbar right">
						<#--页面顶端显示用户信息-->
						<#include "/template/topUserInfo.ftl">
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</#if>
		<div class="row border-bottom">
			<div class="box">
				<div class="logo">
					<a href="javascript:void(0);"></a>
					<span>一户通</span>
				</div>
				<#if Session["session_user"]??>
					<div class="nav">
						<#include "/template/navigationBar.ftl">
					</div>
				<#else>
					<div class="helplinks">
						<a href="${rca.contextPath}/login/index.html">登录</a> | 
						<a href="${rca.contextPath}/public/linkus.html">联系我们</a>
					</div>
				</#if>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="row bg-gray">
			<div class="box">
			<div class="info info-fail">
				<img src="${rca.contextPath}/img/error.jpg" alt="" />
				<h3>您的操作遇到问题</h3>
				<p>错误信息:(${error_msg_key})</p>
			</div>
			</div>
		</div>
		<#include "/template/bottom.ftl">
	</body>
</html>