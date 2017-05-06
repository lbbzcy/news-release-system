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
			       <div class="liutext for-cur"><em>1</em><br /><strong>填写账户名</strong></div>
			       <div class="liutext for-cur"><em>2</em><br /><strong>验证身份</strong></div>
			       <div class="liutext"><em>3</em><br /><strong>设置新密码</strong></div>
			       <div class="liutext"><em>4</em><br /><strong>完成</strong></div>
			      </div>
			     </div><!--for-liucheng/-->
			     <form action="${rca.contextPath}/retrievepwd/step3.html" method="get" class="forget-pwd">
			       <dl>
			        <dt>验证方式：</dt>
			        <dd>
			         <select class="selyz">
			          <option value="0">已验证手机</option>
			          <option value="1">已验证邮箱</option>
			         </select>
			        </dd>
			        <div class="clears"></div>
			       </dl>
			       <dl>
			        <dt>用户名：</dt>
			        <dd><input type="text" /></dd>
			        <div class="clears"></div>
			       </dl>
			       <dl class="sel-yzsj">
			        <dt>已验证手机：</dt>
			        <dd><input type="text" value="1851****517" readonly  /></dd>
			        <div class="clears"></div>
			       </dl>
			       <dl class="sel-yzyx">
			        <dt>已验证邮箱：</dt>
			        <dd><input type="text" value="764852123@qq.com" readonly /></dd>
			        <div class="clears"></div>
			       </dl>
			       <dl>
			        <dt>手机校验码：</dt>
			        <dd><input type="text" /> <button>获取短信验证码</button></dd>
			        <div class="clears"></div>
			       </dl>
			       <div class="subtijiao"><input type="submit" value="提交" /></div> 
			      </form><!--forget-pwd/-->
			   </div><!--web-width/-->
			  </div><!--content/-->
		</div>
		<script type="text/javascript">
			 //导航定位
			 $(function(){
				// $(".nav li:eq(2) a:first").addClass("navCur")
				 //验证手机 邮箱 
				 $(".selyz").change(function(){
				   var selval=$(this).find("option:selected").val();
					 if(selval=="0"){
						 $(".sel-yzsj").show()
						 $(".sel-yzyx").hide()
						 }
					 else if(selval=="1"){
						 $(".sel-yzsj").hide()
						 $(".sel-yzyx").show()
						 }
					 })
				 })
			</script>
		<!-- CONTENT END -->
		<#include "/common/footer.ftl">
	</div>
	<#include "/common/popup.ftl">
</body>

</html>