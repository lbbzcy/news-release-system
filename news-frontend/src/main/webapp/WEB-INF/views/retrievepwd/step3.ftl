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
			       <div class="liutext for-cur"><em>1</em><br /><strong>填写用户名</strong></div>
			       <div class="liutext for-cur"><em>2</em><br /><strong>验证身份</strong></div>
			       <div class="liutext for-cur"><em>3</em><br /><strong>设置新密码</strong></div>
			       <div class="liutext"><em>4</em><br /><strong>完成</strong></div>
			      </div>
			     </div><!--for-liucheng/-->
			     <form id="pwd_form" action="${rca.contextPath}/retrievepwd/step4.html" method="post" class="forget-pwd">
			       <dl>
			        <dt>用户名：</dt>
			        <dd>
			        	<input type="text" value="${user.username}"/>
			        	<input name="userid" type="hidden" value="${user.id}"/>
			        </dd>
			        <div class="clears"></div>
			       </dl> 
			       <dl>
			        <dt>新密码：</dt>
			        <dd>
			        	<input name="password1" id="password1" type="password" />
			        	<input name="password" id="password" type="hidden" />
			        </dd>
			        <div class="clears"></div>
			       </dl> 
			       <dl>
			        <dt>确认密码：</dt>
			        <dd><input name="confirmpwd" id="confirmpwd" type="password" /></dd>
			        <div class="clears"></div>
			       </dl> 
			       <div class="subtijiao"><input type="button" id="inputpwd" value="提交" /></div> 
			      </form><!--forget-pwd/-->
			   </div><!--web-width/-->
			  </div><!--content/-->
			  <script type="text/javascript">
			  	$(function(){
			  		var errormsg = "${errormsg}";
			  		if(!validata(errormsg)){
			  			layer.msg(errormsg, {
							time : 2000,
							offset : '100px'
						});
			  		}
			  		$('#pwd_form').validate({
			  			rules:{
			  				password1:{
		    	   				required:true,
		    	   				rangelength:[6,20]
		    	   			},
		    	   			confirmpwd:{
		    	   				required:true,
		    	   				rangelength:[6,20],
		    	   				equalTo:"#password1"
		    	   			}
			  			},
			  			messages:{
			  				password1:{
		    	        		required:"请输入密码",
		    	        		rangelength:"密码长度在{0}-{1}之间"
		    	        	},
		    	        	confirmpwd:{
		    	        		required:"请确认密码",
		    	        		rangelength:"密码长度在{0}-{1}之间",
		    	        		equalTo:"两次输入的密码不一致"
		    	        	}
			  			}
			  		});
			  	});
			  	$('#inputpwd').click(function(){
			  		$("#password").val($.md5($.trim($("#password1").val())));
    				$("#pwd_form").submit();
			  	});
			  </script>
		</div>
		<!-- CONTENT END -->
		<#include "/common/footer.ftl">
	</div>
	<#include "/common/popup.ftl">
</body>

</html>