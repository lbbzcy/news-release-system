<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/mgr_header.jsp"%>
<body class="gray-bg" onkeydown="handleEnter(event)">
	<div class="middle-box text-center loginscreen  animated fadeInDown">
		<div>
			<div>
				<h1 class="logo-name">M</h1>
			</div>
			<h3>管理员登录</h3>
			<c:if test="${not empty msg }">
				<div class="alert alert-danger alert-dismissable">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">×</button>
					<div class="ipt-error" style="display: none;">
						<jsp:include page="login_error.jsp" />
					</div>
				</div>
			</c:if>
			<div class="ipt-error" style="display: none;">
				<jsp:include page="login_error.jsp" />
			</div>
			<form id="login_form" class="m-t" role="form"
				action="${basepath}/login.html" method="post">
				<input type="hidden" id="j_password" name="j_password" />
				<div class="form-group">
					<input type="text" class="form-control" id="j_username"
						name="j_username" placeholder="用户名">
				</div>
				<div class="form-group">
					<input id="password" type="password" class="form-control"
						name="password" value="" placeholder="密码">
				</div>
				<a href="javascript:void(0)" onclick="login();"
					class="btn btn-success block full-width m-b">登 录</a>
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/mgr_script.jsp"%>
	<script type="text/javascript">
		$(function() {
			$("#username,#password").keydown(function() {
				$('div.ipt-error').hide(200);
				$('div.ipt-error').html('');
			});
			//后台错误显示
			if ($.trim($('div.ipt-error').text()) != '') {
				$('div.ipt-error').show(200);
			}
		});

		/**
		 * 显示提示信息
		 */
		function showMessage(msg) {
			$('div.ipt-error').text(msg);
			$('div.ipt-error').show(200);
		}
		function login() {
			if ($.trim($("#j_username").val()) == '') {
				showMessage("账号不能为空！");
				$("#j_username").focus();
				return false;
			} else if ($.trim($("#password").val()) == '') {
				console.log("密码不能为空！");
				showMessage("密码不能为空！");
				$("#password").focus();
				return false;
			}
			$("#j_password").val($.md5($.trim($("#password").val())));
			$("#login_form").submit();
			return true;
		}
		function handleEnter(event) {
			var keyCode = event.keyCode ? event.keyCode
					: event.which ? event.which : event.charCode;
			if (keyCode == 13) {
				login();
			}
		}
	</script>
</body>
</html>
