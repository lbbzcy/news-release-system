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
							<li class="current"><a href="#login" class="open_popup">登录</a></li>
							<li><a href="${rca.contextPath}/register/index.html">注册</a></li>
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
					<div class="block_languages"></div>
					<div class="block_search_top">
						<form action="#" />
						<div class="field">
							<input type="text" class="w_def_text" title="搜索" />
						</div>
						<input type="submit" class="button" value="Search" />
						<div class="clearboth"></div>
						</form>
					</div>
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
								<li><a href="${rca.contextPath}/newstype/index.html">${item.name}</a></li>
							</#if>
						</#list>
						<#if (data?size > 11)>
						<li><a href="">更多</a>
							<ul>
								<#list data as item>
									<#if item_index gte 11>
										<li><a href="${rca.contextPath}/newstype/index.html">${item.name}</a></li>
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