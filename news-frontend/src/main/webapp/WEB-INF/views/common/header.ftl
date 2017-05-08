<!-- HEADER BEGIN -->
<header>
	<div id="header">
		<section class="top">
			<div class="inner">
				<div class="fl">
					<div class="block_top_menu">
						<ul>
							<li class="current"><a href="${rca.contextPath}/main/index.html">主页</a></li>
						</ul>
					</div>
				</div>
				<div class="fr">
					<div class="block_top_menu">
						<ul>
							<#if Session["login_user"]?exists>
								<li class="current" style="width:130px;text-align:center;">
									<a class="personal">${Session["login_user"].username}</a>
									<div class="user-layer"> 
										<ul> 
											<li><a class="layer-item" rel="nofollow" href="${rca.contextPath}/personal/index.html">个人中心</a></li> 
											<li><a href="${rca.contextPath}/login/logout.html" class="layer-item" rel="nofollow">退出</a> </li> 
										</ul> 
									</div>
								</li>
							<#else>
								<li class="current"><a href="#login" class="open_popup">登录</a></li>
							</#if>
							<li style="border:none;"><a href="${rca.contextPath}/register/index.html">注册</a></li>
						</ul>
					</div>
				</div>
				<div class="clearboth"></div>
			</div>
		</section>
		<section class="bottom">
			<div class="inner">
				<div id="logo_top">
					<a href="index.html"><img src="${rca.contextPath}/images/logo_top.png"
						alt="BusinessNews" title="BusinessNews" /></a>
				</div>
				<div class="fr">
					<div class="block_search_top">
						<form action="${rca.contextPath}/solr/search.html" onsubmit="return checkQuery();"/>
							<div class="field">
								<input name="queryParam" type="text" class="w_def_text" placeholder="输入搜索内容" />
							</div>
							<input type="submit" class="button" value="Search" />
							<div class="clearboth"></div>
						</form>
					</div>
					<script>
						$(function(){
							$(".personal").hover(function(){
							    $(".user-layer").css("display","block");
							},function(){
							   
							});
							$(".user-layer").hover(function(){
							    $(".user-layer").css("display","block");
							},function(){
							    $(".user-layer").css("display","none");
							});
						});
						//检查搜索的内容是否为空
						function checkQuery(){
							var inputQuery = $('.w_def_text').val();
							if (validata(inputQuery)) {
								layer.msg('请输入搜索的内容', {
									time : 2000,
									offset : '100px'
								});
								return false;
							}
						}
					</script>
				</div>
				<div class="clearboth"></div>
			</div>
		</section>

		<section class="section_main_menu">
			<div class="inner">
				<nav class="main_menu">
					<ul>
						<#list data as item>
							<#if item_index lt 11>
								<li><a href="${rca.contextPath}/newstype/index.html?typeid=${item.id}&typename=${item.name}">${item.name}</a></li>
							</#if>
						</#list>
						<#if (data?size > 11)>
						<li><a href="">更多</a>
							<ul>
								<#list data as item>
									<#if item_index gte 11>
										<li><a href="${rca.contextPath}/newstype/index.html?typeid=${item.id}&typename=${item.name}">${item.name}</a></li>
									</#if>
								</#list>
							</ul>
						</li>
						</#if>
					</ul>
				</nav>
			</div>
		</section>
	</div>
</header>
<!-- HEADER END -->