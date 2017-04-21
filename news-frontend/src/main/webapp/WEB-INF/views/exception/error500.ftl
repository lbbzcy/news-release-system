<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="renderer" content="webkit"> 
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>错误提示页</title>
		<link rel="stylesheet" href="${Request["contextPath"]}/css/style.css" />
		<!--[if lte IE 9]>  
			<script src="js/respond.js"></script>  
			<script src="js/html5shiv.js"></script>  
		<![endif]-->
	</head>
	<body>
		<!--<div class="row border-bottom">
			<div class="box">
				<div class="logo">
					<a href="javascript:void(0);"></a>
					<span>一户通</span>
				</div>
				<div class="helplinks">
					<a href="${Request["contextPath"]}/login/index.html" class="active">登录</a> | 
					<a href="${Request["contextPath"]}/public/linkus.html">联系我们</a>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>-->
		<div class="row bg-gray">
			<div class="box">
			   <div class="errorpage">
			   	  <h2><span>500</span>网页不知去向，无法打开！</h2>
			   	  
			   	  <div class="left">
			   	  	<h3>可能原因：</h3>
			   	  	
			   	  	1、网络信号差 <br />
			   	  	2、找不到请求的页面 <br />
			   	  	3、输入网址不正确
			   	  </div>
			   	  <div class="right">
			   	  	<h3>可以尝试：</h3>
			   	  	<a href="${Request["contextPath"]}/main/index.html"><i class="icon icon-angle-right"></i>返回首页>></a>
			   	  </div>
			   	  <div class="clearfix"></div>
			   </div>
			
			</div>
		</div>
	</body>
	</script>

</html>