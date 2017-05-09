<!-- POPUP BEGIN -->
<div id="overlay"></div>
<div id="login" class="block_popup">
    <div class="popup">
        <a href="#" class="close">关闭</a>

        <div class="content">
            <div class="title">
            	<p>登录网站<span style="color:red;text-align:center;margin-left:20px;" id="login_error"></span></p>
            </div>

            <div class="form">
                <form id="loginForm" />
                <div class="column">
                    <p class="label">用户名/手机号/邮箱</p>
                    <div class="field"><input id="username" name="username" type="text" /></div>
                </div>

                <div class="column">
                    <p class="label">密码</p>
                    <div class="field"><input id="password" name="password" type="password" /></div>
                </div>

                <div class="column_2">
                    <div class="remember">
                        <div class="checkbox"><input type="checkbox" /></div>
                        <div class="remember_label"><p>记住我</p></div>
                    </div>
                </div>

                <div class="column_2">
                    <p class="forgot_pass"><a href="${rca.contextPath}/retrievepwd/step1.html">忘记密码?</a></p>
                </div>

                <div class="column button">
                    <a href="javascript:void(0);" onclick="login();" class="enter"><span>登录</span></a>
                </div>

                <div class="clearboth"></div>
                </form>
            </div>
            
            <script type="text/javascript">
            	function login(){
            		var username = $("#username").val();
            		var password = $.md5($.trim($("#password").val()));
            		$.ajax({
            			type:"post",
            			url:"${rca.contextPath}/login/handleLogin.html",
            			data:{
            				username:username,
            				password:password
            			},
            			dataType:"json",
            			success:function(data){
            				if(data=="success"){
            					console.log("登录成功");
            					window.location.reload();
            				}else{
            					$('#login_error').html("用户名或密码出错,请稍后再试!");
            					setTimeout(function(){
            						$('#login_error').html("");
            					},3000);
            				}
            			},
            			error: function(XMLHttpRequest, textStatus, errorThrown) {
							 console.log(XMLHttpRequest.status);
							 console.log(XMLHttpRequest.readyState);
							 console.log(textStatus);
						}
            		});
            	}
            </script>

            <!--<div class="subtitle"><p>以用户身份登录</p></div>

            <div class="fb_button"><a href="#"><img src="images/button_fb_login.png" alt="" /></a></div>
            <div class="text"><p>Use your account on the social network Facebook, to create a profile on BusinessPress</p></div>-->
        </div>
    </div>
</div>
<!-- POPUP END -->