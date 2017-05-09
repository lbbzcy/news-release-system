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
			      <div class="liulist"></div>
			      <div class="liulist"></div>
			      <div class="liulist"></div>
			      <div class="liutextbox">
			       <div class="liutext for-cur"><em>1</em><br /><strong>填写用户名</strong></div>
			       <div class="liutext"><em>2</em><br /><strong>验证身份</strong></div>
			       <div class="liutext"><em>3</em><br /><strong>设置新密码</strong></div>
			       <div class="liutext"><em>4</em><br /><strong>完成</strong></div>
			      </div>
			     </div><!--for-liucheng/-->
			     <form id="pwd_from" action="${rca.contextPath}/retrievepwd/step2.html" method="get" class="forget-pwd" onsubmit="return check();">
			       <dl>
			        <dt>身份认证：</dt>
			        <dd>
			        	<input id="username" name="username" type="text" />
			        	<span id="usernamemsg" style="margin-left:5px;color:#ff6600;"></span>
			        </dd>
			        <div class="clears"></div>
			       </dl> 
			       <dl>
			        <dt>验证码：</dt>
			        <dd>
			         <input id="imgcode" name="imgcode" type="text" maxlength="6" /> 
			         <div class="yanzma">
			          <img id="validatecode" onclick="if(this.src!=this.src+'?'+Math.random) this.src=this.src+'?'+Math.random();" src="${rca.contextPath}/getCode.html" />
			          <a onclick="change();" href="javascript:void(0);">换一换</a>
			          <span id="imgcodemsg" style="margin-left:5px;color:#ff6600;"></span>
			         </div>
			        </dd>
			        <div class="clears"></div> 
			       </dl>
			       <div class="subtijiao"><input type="submit" value="提交" /></div> 
			      </form><!--forget-pwd/-->
			   </div><!--web-width/-->
			  </div><!--content/-->
			</div>
		</div>
		<script type="text/javascript">
			//用户名和验证码失去焦点事件
			$('#username').blur(function(){
				var username = $('#username').val();
				checkusername(username);
			});
			$('#imgcode').blur(function(){
				var imgcode = $('#imgcode').val();
				checkimgcode(imgcode);
			});
			//按钮提交的验证
			function check(){
				var username = $('#username').val();
				var imgcode = $('#imgcode').val();
				
				var result = checkusername(username);
				var result1 = checkimgcode(imgcode);
				if(result==true && result1==true){
					return true;
				}else{
					return false;
				}
			}
			//验证验证码
			function checkimgcode(imgcode){
				var result = true;
				if(validata(imgcode)){
					$('#imgcodemsg').html("请填写验证码");
					result = false;
				}else{
					result = validcode(imgcode);
					console.log(result);
					if(result){
						$('#imgcodemsg').html('');
					}else{
						$('#imgcodemsg').html("验证码出错");
					}
				}
				return result;
			}
			//验证用户名
			function checkusername(username){
				var result = true;
				if(validata(username)){
					$('#usernamemsg').html("用户名、手机号、邮箱");
					result = false;
				}else{
					result = isuservalid(username);
					console.log(result);
					if(result){
						$('#usernamemsg').html('');
					}else{
						$('#usernamemsg').html("用户不存在");
					}
				}
				return result;
			}
			//验证码是否正确
			function validcode(imgcode){
			    var result = false;
				$.ajax({
					type:"POST",
					async: false, 
                    url:"${rca.contextPath}/retrievepwd/validCode.html",
                    data:{
                	   imgcode:imgcode
                    },
                    dataType:"json",
                    success:function(data){
                    	if(data=="success"){
                    		result =  true;
                    		console.log("验证码正确");
                    	}else{
                    		result =  false;
                    		console.log("验证码出错");
                    	}
                    }
				});
				return result;
			}
			//用户是否存在
			function isuservalid(username){
			    var result = false;
				$.ajax({
					type:"POST",
					async: false, 
                    url:"${rca.contextPath}/retrievepwd/checkUser.html",
                    data:{
                	   username:username
                    },
                     dataType:"json",
                    success:function(data){
                    	if(data=="false"){
                    		result =  true;
                    		console.log("用户存在");
                    	}else{
                    		result =  false;
                    		console.log("用户不存在");
                    	}
                    }
				});
				return result;
			}
			//刷新验证码
			function change(){
				$("#validatecode").attr('src',"${rca.contextPath}/getCode.html?"+Math.random()); 
			}
		</script>
		<!-- CONTENT END -->
		<#include "/common/footer.ftl">
	</div>
	<#include "/common/popup.ftl">
</body>

</html>