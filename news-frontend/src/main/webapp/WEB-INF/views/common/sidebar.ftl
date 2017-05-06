<div class="sidebar">
	<div class="block_subscribes_sidebar"></div>

	<div class="separator" style="height: 31px;"></div>

	<div class="block_popular_posts">
		<h4>热门新闻</h4>
		<#list hotNewsList as hot>
			<div class="article">
	
				<div class="text">
					<p class="title">
						<a href="${rca.contextPath}/newsdetail/index.html?newsid=${hot.id}">${hot.title}</a>
					</p>
					<div class="date">
						<p>${hot.createtime?date}</p>
					</div>
					<div class="icons">
						<ul>
							<li><a href="#" class="views">${hot.viewnum}</a></li>
							<li><a href="#" class="comments">${hot.commentnum}</a></li>
						</ul>
					</div>
				</div>
			</div>
			<#if hot_index == hotNewsList?size>
				<div class="line_3"></div>
			<#else>
				<div class="line_2"></div>
			</#if>
		</#list>
	</div>

	<div class="separator" style="height: 31px;"></div>

	<div class="block_popular_stuff">
		<h4>热门视频</h4>

		<div class="content">
			<a href="${rca.contextPath}/media/index.html" class="view_all">显示所有视频</a>
			<div class="media">
				<a href="http://www.youtube.com/watch?v=ySIvism2af8"
					class="general_pic_hover play no_fx" data-rel="prettyPhoto"
					title="Popular Video"><img src="${rca.contextPath}/images/pic_pop_video.jpg"
					alt="" /></a>
			</div>
			<p>
				<a href="blog_post_w_video.html">Publishing packages and
					web page editors their default model.</a> <img
					src="${rca.contextPath}/images/icon_video.gif" alt="" />
			</p>
			<p class="date">11 July, 2012</p>
		</div>

		<div class="info">
			<ul>
				<li class="comments"><a href="#">115</a></li>
				<li class="views"><a href="#">220</a></li>
			</ul>
		</div>

		<div class="clearboth"></div>

		<div class="line_2"></div>
	</div>

	<div class="separator" style="height: 31px;"></div>


	<div class="separator" style="height: 31px;"></div>


	<div class="block_popular_stuff">
		<h4>热门图片</h4>
		<#list picNewsList as pic>
			<div class="content">
				<a href="${rca.contextPath}/media/index.html" class="view_all">显示所有图片</a>
				<div class="media">
					<a href="${rca.contextPath}/images/pic_pop_photo_big.jpg"
						class="general_pic_hover zoom no_fx" data-rel="prettyPhoto"
						title="Popular Photo"><img src="${rca.contextPath}/images/pic_pop_photo.jpg"
						alt="" /></a>
				</div>
				<p>
					<a href="${rca.contextPath}/newsdetail/index.html?newsid=${pic.id}">${pic.title}</a> <img
						src="${rca.contextPath}/images/icon_photo.gif" alt="" />
				</p>
				<p class="date">${pic.createtime?date}</p>
			</div>
	
			<div class="info">
				<ul>
					<li class="comments"><a href="#">${pic.commentnum}</a></li>
					<li class="views"><a href="#">${pic.viewnum}</a></li>
				</ul>
			</div>
	
			<div class="clearboth"></div>
		</#list>
		<div class="line_2"></div>
	</div>
</div>

<div class="clearboth"></div>