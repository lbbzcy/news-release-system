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
			      <div class="liulist for-cur"></div>
			      <div class="liulist"></div>
			      <div class="liutextbox">
			       <div class="liutext for-cur"><em>1</em><br /><strong>填写账户名</strong></div>
			       <div class="liutext for-cur"><em>2</em><br /><strong>验证身份</strong></div>
			       <div class="liutext for-cur"><em>3</em><br /><strong>设置新密码</strong></div>
			       <div class="liutext"><em>4</em><br /><strong>完成</strong></div>
			      </div>
			     </div><!--for-liucheng/-->
			     <form action="${rca.contextPath}/retrievepwd/step4.html" method="get" class="forget-pwd">
			       <dl>
			        <dt>手机号：</dt>
			        <dd><input type="text" /></dd>
			        <div class="clears"></div>
			       </dl> 
			       <dl>
			        <dt>新密码：</dt>
			        <dd><input type="password" /></dd>
			        <div class="clears"></div>
			       </dl> 
			       <dl>
			        <dt>确认密码：</dt>
			        <dd><input type="password" /></dd>
			        <div class="clears"></div>
			       </dl> 
			       <div class="subtijiao"><input type="submit" value="提交" /></div> 
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