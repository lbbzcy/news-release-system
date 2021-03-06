<!DOCTYPE html>
<html>

<head>
<title>BusinessNews</title>

<meta name="keywords" content="" />
<meta name="description" content="" />

<meta charset="utf-8" />
<meta name="viewport" content="width=device-width" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "/common/allJsAndCSS.ftl">
<link rel="stylesheet" href="${rca.contextPath}/css/retrievepwd.css" />
</head>
<body>
	<div class="wrapper sticky_footer">
		<#include "/common/header.ftl"> 
		<!-- CONTENT BEGIN -->
		<div id="content" class="right_sidebar">
			 <div class="content">
			   <div class="web-width">
			     <div class="for-liucheng">
			      <div class="liulist for-cur"></div>
			      <div class="liulist for-cur"></div>
			      <div class="liulist"></div>
			      <div class="liulist"></div>
			      <div class="liutextbox">
			       <div class="liutext for-cur"><em>1</em><br /><strong>填写用户名</strong></div>
			       <div class="liutext for-cur"><em>2</em><br /><strong>验证身份</strong></div>
			       <div class="liutext"><em>3</em><br /><strong>设置新密码</strong></div>
			       <div class="liutext"><em>4</em><br /><strong>完成</strong></div>
			      </div>
			     </div><!--for-liucheng/-->
			     <form class="forget-pwd">
			       <dl class="sel-yzsj">
			        <dt>已验证邮箱：</dt>
			        <dd><input type="text" value="${user.email}" readonly  /></dd>
			        <div class="clears"></div>
			       </dl>
			       <dl>
			        <dt>用户名：</dt>
			        <dd><input type="text" value="${user.username}" /></dd>
			        <div class="clears"></div>
			       </dl>
			       <div class="subtijiao" style="color:#ff6600;height:30px;line-height:30px;font-size:14px;">邮件已经发送至该邮箱，请登录该邮箱进行密码找回！</div> 
			      </form><!--forget-pwd/-->
			   </div><!--web-width/-->
			  </div><!--content/-->
		</div>
		<!-- CONTENT END -->
		<#include "/common/footer.ftl">
	</div>
	<#include "/common/popup.ftl">
</body>

</html>