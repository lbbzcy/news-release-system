<!DOCTYPE html>
<html lang="zh-CN">

<head>
<title>BusinessNews - Registration</title>

<meta name="keywords" content="" />
<meta name="description" content="" />

<meta charset="utf-8" />
<meta name="viewport" content="width=device-width" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "/common/allJsAndCSS.ftl">
</head>
<body>
	<div class="wrapper sticky_footer">
		<#include "/common/header.ftl"> <!-- CONTENT BEGIN -->
		<div id="content" class="">
			<div class="inner">
				<div class="general_content">
					<div class="main_content">
						<div class="block_breadcrumbs">
							<div class="text">
								<p>您现在的位置:</p>
							</div>

							<ul>
								<li><a href="index.html">主页</a></li>
								<li>注册</li>
							</ul>
						</div>
						<div class="separator" style="height: 28px;"></div>

						<p class="general_title">
							<span>加入我们</span>
						</p>
						<div class="separator" style="height: 39px;"></div>

						<div class="block_registration">
							<form action="#" id="w_validation" method="post"/>
							<div class="col_1">
								<div style="margin:0px 205px;width:450px;">
									<div class="label">
										<p>
											用户名<span>*</span>:
										</p>
									</div>
									<input name="username" id="username" type="text" maxlength="20" />
									<div class="clearboth"></div>
									<div class="separator" style="height: 14px;"></div>
	
									<div class="label">
										<p>
											昵称<span>*</span>:
										</p>
									</div>
									<input name="nickname" id="nickname" type="text" maxlength="20" />
									<div class="clearboth"></div>
									<div class="separator" style="height: 14px;"></div>
													
									<div class="label">
										<p>
											手机号<span>*</span>:
										</p>
									</div>
									<input name="mobile" id="mobile" type="text" maxlength="11" />
									<div class="clearboth"></div>
									<div class="separator" style="height: 12px;"></div>
									
									<div class="label">
										<p>
											邮箱<span>*</span>:
										</p>
									</div>
									<input name="email" id="email" type="text" />
									<div class="clearboth"></div>
									<div class="separator" style="height: 12px;"></div>
	
									<div class="label">
										<p>
											密码<span>*</span>:
										</p>
									</div>
									<input name="password" id="password" type="password" />
									<div class="clearboth"></div>
									<div class="separator" style="height: 12px;"></div>
									
									<div class="label">
										<p>
											确认密码<span>*</span>:
										</p>
									</div>
									<input name="confirmpwd" id="confirmpwd" type="password" />
									<div class="clearboth"></div>
									<div class="separator" style="height: 12px;"></div>
									
								</div>
							</div>


							<div class="clearboth"></div>
							<div class="separator" style="height: 32px;"></div>

							<p class="info_text">
								通过点击“注册”按钮，您同意成为服务条款(<a href="#">用户协议</a>)
							</p>
							<p class="info_text">
								<input type="button" onclick="register();" class="general_button" value="注册" />
							</p>
							</form>
						</div>

						<div class="line_3" style="margin: 42px 0px 0px;"></div>

					</div>

					<div class="clearboth"></div>
				</div>
			</div>
		</div>
		<!-- CONTENT END --> 
		<#include "/common/footer.ftl">
	</div>
	<#include "/common/popup.ftl"> 
	<script type="text/javascript">
    	$(function(){
    		// 手机号码验证
    		jQuery.validator.addMethod("isMobile", function(value, element) {
    		    var length = value.length;
    		    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
    		    return this.optional(element) || (length == 11 && mobile.test(value));
    		}, "请正确填写您的手机号码");
    		$("#w_validation").validate({  
    	        rules: {  
    	   			username:{
    	   				required:true,
    	   				rangelength:[6,20],
    	   				remote:{
    	   				//验证用户名是否已经  
     	                   type:"POST",  
     	                   url:"${rca.contextPath}/register/checkUserName.html",
     	                   data:{
     	                	  username:function(){
     	                		   return $("#username").val();
     	                	   }
     	                   }
    	   				}
    	   			},
    	   			nickname:{
    	   				required:true,
    	   				rangelength:[1,20]
    	   			},
    	   			mobile:{
    	   				required:true,
    	   				isMobile:true
    	   			},
    	   			email:{
    	   				required:true,
    	   				email:true
    	   			},
    	   			password:{
    	   				required:true,
    	   				rangelength:[6,20]
    	   			},
    	   			confirmpwd:{
    	   				required:true,
    	   				rangelength:[6,20],
    	   				equalTo:"#password"
    	   			}
    	   			
    			},  
    	        messages: {  
    	        	username:{
    	        		required:"请输入用户名",
    	        		rangelength:"用户名长度必须在{0}-{1}之间",
    	        		remote:"用户名已经被注册"
    	        	},
    	        	nickname:{
    	        		required:"请输入昵称",
    	        		rangelength:"昵称长度在{0}-{1}之间"
    	        	},
    	        	mobile:{
    	        		required:"请输入手机号码"
    	        	},
    	        	email:{
    	        		required:"请输入邮箱",
    	        		email:"邮箱格式不正确"
    	        	},
    	        	password:{
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
    	function register(){
    		$("#w_validation").submit();
    	}
    </script>
</body>

</html>