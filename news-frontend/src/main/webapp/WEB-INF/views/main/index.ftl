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
</head>
<body>
	<div class="wrapper sticky_footer">
		<#include "/common/header.ftl"> 
		<!-- CONTENT BEGIN -->
		<div id="content" class="right_sidebar">
			<div class="inner">
				<div class="general_content">
					<div class="main_content">
						<div class="block_special_topic">
							<div class="type">
								<p>专题</p>
							</div>
							<div class="title">
								<p>
									<a href="#">长期以来的事实，读者会分心。</a>
								</p>
							</div>
						</div>
						<div class="separator" style="height: 17px;"></div>

						<div class="block_home_slider">
							<div id="home_slider" class="flexslider">
								<ul class="slides">
									<#list bannerList as item>
										<li>
											<div class="slide">
												<img style="width:610px;height:292px;" src="${BANNER_IMGSRC}${item.imgsrc}" alt="" />
												<div class="caption">
													<p class="title">${item.title}</p>
													<p>${item.briefintroduction}</p>
												</div>
											</div>
										</li>
									</#list>
								</ul>
							</div>

							<script type="text/javascript">
                        $(function () {
                            $('#home_slider').flexslider({
                                animation : 'slide',
                                controlNav : true,
                                directionNav : true,
                                animationLoop : true,
                                slideshow : false,
                                useCSS : false
                            });

                        });
                    </script>
						</div>

						<div class="line_2" style="margin: 34px 0px 28px;"></div>
						<div class="clearboth"></div>

						<h3 style="font-size: 16px;">新闻列表<a href="${rca.contextPath}/main/main_news.html" class="lnk_all_news fr">所有新闻</a></h3>
						<div class="line_4" style="margin: -4px 0px 18px;"></div>

						<div class="block_topic_news">
							<#list newsDetailList as item>
								<article class="block_topic_post">
									<p class="title">
										<a href="${rca.contextPath}/newsdetail/index.html">${item.title}</a>
									</p>
									<p class="text">${item.brief}</p>
									<div class="info">
										<div class="date">
											<p>${item.createtime?date}</p>
										</div>
	
										<div class="r_part">
											<div class="category">
												<p>
													<a href="#">${item.typename}</a>
												</p>
											</div>
											<a href="#" class="views">${item.viewnum}</a>
										</div>
									</div>
								</article>
							</#list>

						</div>

						<div class="line_3" style="margin: 20px 0px 24px;"></div>

						<#include "/common/pagination.ftl">
						
						<div class="line_2" style="margin: 24px 0px 35px;"></div>

					</div>

					<#include "/common/sidebar.ftl">
				</div>
			</div>
		</div>
		<!-- CONTENT END -->
		<#include "/common/footer.ftl">
	</div>
	<#include "/common/popup.ftl">
	</body>

</html>