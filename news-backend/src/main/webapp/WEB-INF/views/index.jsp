<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/views/include/mgr_header.jsp"%>
<body class="fixed-sidebar full-height-layout gray-bg"
	style="overflow: hidden">
	<div id="wrapper">
		<!--左侧导航开始-->
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="nav-close">
				<i class="fa fa-times-circle"></i>
			</div>
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">
					<li class="nav-header">
						<div class="dropdown profile-element">
							<span><img alt="image" class="img-circle"
								src="${basepath}/img/profile_small.jpg" /></span> <a
								data-toggle="dropdown" class="dropdown-toggle" href="#"> <span
								class="clear"> <span class="block m-t-xs"><strong
										class="font-bold">${user.name}</strong></span> <span
									class="text-muted text-xs block">超级管理员<b class="caret"></b></span>
							</span>
							</a>
							<ul class="dropdown-menu animated fadeInRight m-t-xs">
								<li><a href="${basepath}/logout.html">安全退出</a></li>
							</ul>
						</div>
						<div class="logo-element">H+</div>
					</li>
					<li><a href="#"> <i class="fa fa-home"></i> <span
							class="nav-label">新闻管理</span> <span class="fa arrow"></span>
					</a>
						<ul class="nav nav-second-level">
							<li><a class="J_menuItem"
								href="${basepath}/news_type/list.html" data-index="1">类别管理</a></li>
							<li><a class="J_menuItem" href="${basepath}/template/list.html">模板管理</a>
							</li>
							<li><a class="J_menuItem" href="${basepath}/news_edit/list.html">新闻编辑</a>
							</li>
							<li><a class="J_menuItem" href="${basepath}/comment/list.html">评论管理</a>
							</li>
						</ul></li>
					<li><a class="J_menuItem" href="${basepath}/user/list.html"><i
							class="fa fa-columns"></i> <span class="nav-label">用户管理</span></a></li>
					<li><a class="J_menuItem" href="${basepath}/banner/list.html"><i
							class="fa fa-columns"></i> <span class="nav-label">banner管理</span></a>
					</li>
				</ul>
			</div>
		</nav>
		<!--左侧导航结束-->
		<!--右侧部分开始-->
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
							href="index.html#"><i class="fa fa-bars"></i> </a>

					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li><span class="m-r-sm text-muted welcome-message"><a
								href="index.html" title="返回首页"><i class="fa fa-home"></i></a>欢迎使用新闻发布系统后台</span>
						</li>
						<li><a href="${basepath}/logout.html"> <i
								class="fa fa-sign-out"></i> 退出
						</a></li>
					</ul>

				</nav>
			</div>
			<div class="row content-tabs">
				<button class="roll-nav roll-left J_tabLeft">
					<i class="fa fa-backward"></i>
				</button>
				<nav class="page-tabs J_menuTabs">
					<div class="page-tabs-content">
						<a href="javascript:;" class="active J_menuTab"
							data-id="${basepath}/index_v1.html">首页</a>
					</div>
				</nav>
				<button class="roll-nav roll-right J_tabRight">
					<i class="fa fa-forward"></i>
				</button>
				<div class="btn-group roll-nav roll-right">
					<button class="dropdown J_tabClose" data-toggle="dropdown">
						关闭操作<span class="caret"></span>

					</button>
					<ul role="menu" class="dropdown-menu dropdown-menu-right">
						<li class="J_tabShowActive"><a>定位当前选项卡</a></li>
						<li class="divider"></li>
						<li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
						<li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
					</ul>
				</div>
				<a href="${basepath}/logout.html"
					class="roll-nav roll-right J_tabExit"><i
					class="fa fa fa-sign-out"></i> 退出</a>
			</div>
			<div class="row J_mainContent" id="content-main">
				<iframe class="J_iframe" name="iframe0" width="100%" height="100%"
					src="${basepath}/index_v1.html" frameborder="0"
					data-id="${basepath}/index_v1.html" seamless></iframe>
			</div>
			<div class="footer">
				<div class="pull-right">&copy; 2017-2019</div>
			</div>
		</div>
		<!--右侧边栏结束-->
	</div>
	<%@ include file="/WEB-INF/views/include/mgr_script.jsp"%>
	<script>
		$(function() {
			$("body").addClass("skin-1");
			// 左侧菜单固定
			$("body").addClass('fixed-sidebar');
			$('.sidebar-collapse').slimScroll({
				height : '100%',
				railOpacity : 0.9
			});
		});
	</script>
</body>

</html>
