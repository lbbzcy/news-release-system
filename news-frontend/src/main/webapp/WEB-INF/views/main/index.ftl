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

						<h3 style="font-size: 16px;">主要新闻<a href="${rca.contextPath}/main/main_news.html" class="lnk_all_news fr">所有新闻</a></h3>
						<div class="line_4" style="margin: -4px 0px 18px;"></div>

						<div class="block_topic_news">
							<#list newsDetailList as item>
								<article class="block_topic_post">
									<p class="title">
										<a href="${rca.contextPath}/newsdetail/index.html">
											<#if item.title?length lt 18>
												${item.title}
											<#else>
												${item.title[0..18]}...
											</#if>
										</a>
									</p>
									<div class="f_pic">
										<a href="${rca.contextPath}/newsdetail/index.html" class="general_pic_hover scale"><img
											src="${rca.contextPath}/images/pic_home_main_news_1.jpg" alt="" /></a>
									</div>
									<p class="text">
										<#if item.brief?length lt 40>
											${item.brief}
										<#else>
											${item.brief[0..41]}...
										</#if>
									</p>
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

						<div class="block_pager">
							<a href="#" class="prev">Previous</a> <a href="#" class="next">Next</a>

							<div class="pages">
								<ul>
									<li class="current"><a href="#">1</a></li>
									<li><a href="#">2</a></li>
									<li><a href="#">3</a></li>
									<li><a href="#">4</a></li>
									<li><a href="#">5</a></li>
									<li><a href="#">6</a></li>
								</ul>
							</div>

							<div class="clearboth"></div>
						</div>

						<div class="line_2" style="margin: 24px 0px 35px;"></div>

						<h3 style="font-size: 16px;">最佳材料</h3>
						<div class="line_4" style="margin: -4px 0px 18px;"></div>

						<div class="block_best_materials">
							<div class="slider">
								<div id="best_materials_slider" class="flexslider">
									<ul class="slides">
										<li>
											<div class="block_best_material_post">
												<div class="f_pic">
													<a href="${rca.contextPath}/newsdetail/index.html" class="w_hover"><img
														src="${rca.contextPath}/images/pic_home_best_materials_1.jpg" alt="" /><span></span></a>
												</div>
												<p class="title">
													<a href="${rca.contextPath}/newsdetail/index.html">Publishing packages and web
														page editors their.</a>
												</p>
												<div class="info">
													<div class="date">
														<p>08 July, 2012</p>
													</div>
													<div class="category">
														<p>
															<a href="#">Business</a>
														</p>
													</div>
												</div>
											</div>
										</li>

										<li>
											<div class="block_best_material_post">
												<div class="f_pic">
													<a href="${rca.contextPath}/newsdetail/index.html" class="w_hover"><img
														src="${rca.contextPath}/images/pic_home_best_materials_2.jpg" alt="" /><span></span></a>
												</div>
												<p class="title">
													<a href="${rca.contextPath}/newsdetail/index.html">Publishing packages and web
														page editors their.</a>
												</p>
												<div class="info">
													<div class="date">
														<p>08 July, 2012</p>
													</div>
													<div class="category">
														<p>
															<a href="#">People</a>
														</p>
													</div>
												</div>
											</div>
										</li>

										<li>
											<div class="block_best_material_post">
												<div class="f_pic">
													<a href="${rca.contextPath}/newsdetail/index.html" class="w_hover"><img
														src="${rca.contextPath}/images/pic_home_best_materials_3.jpg" alt="" /><span></span></a>
												</div>
												<p class="title">
													<a href="${rca.contextPath}/newsdetail/index.html">Publishing packages and web
														page editors their.</a>
												</p>
												<div class="info">
													<div class="date">
														<p>08 July, 2012</p>
													</div>
													<div class="category">
														<p>
															<a href="#">Technology</a>
														</p>
													</div>
												</div>
											</div>
										</li>

										<li>
											<div class="block_best_material_post">
												<div class="f_pic">
													<a href="${rca.contextPath}/newsdetail/index.html" class="w_hover"><img
														src="${rca.contextPath}/images/pic_home_best_materials_4.jpg" alt="" /><span></span></a>
												</div>
												<p class="title">
													<a href="${rca.contextPath}/newsdetail/index.html">Publishing packages and web
														page editors their.</a>
												</p>
												<div class="info">
													<div class="date">
														<p>08 July, 2012</p>
													</div>
													<div class="category">
														<p>
															<a href="#">Business</a>
														</p>
													</div>
												</div>
											</div>
										</li>

										<li>
											<div class="block_best_material_post">
												<div class="f_pic">
													<a href="${rca.contextPath}/newsdetail/index.html" class="w_hover"><img
														src="${rca.contextPath}/images/pic_home_best_materials_5.jpg" alt="" /><span></span></a>
												</div>
												<p class="title">
													<a href="${rca.contextPath}/newsdetail/index.html">Publishing packages and web
														page editors their.</a>
												</p>
												<div class="info">
													<div class="date">
														<p>08 July, 2012</p>
													</div>
													<div class="category">
														<p>
															<a href="#">People</a>
														</p>
													</div>
												</div>
											</div>
										</li>

										<li>
											<div class="block_best_material_post">
												<div class="f_pic">
													<a href="${rca.contextPath}/newsdetail/index.html" class="w_hover"><img
														src="${rca.contextPath}/images/pic_home_best_materials_6.jpg" alt="" /><span></span></a>
												</div>
												<p class="title">
													<a href="${rca.contextPath}/newsdetail/index.html">Publishing packages and web
														page editors their.</a>
												</p>
												<div class="info">
													<div class="date">
														<p>08 July, 2012</p>
													</div>
													<div class="category">
														<p>
															<a href="#">Technology</a>
														</p>
													</div>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>

							<script type="text/javascript">
                        $(function() {
                            $('#best_materials_slider').flexslider({
                                animation : 'slide',
                                controlNav : false,
                                directionNav : true,
                                animationLoop : false,
                                slideshow : false,
                                itemWidth: 213,
                                itemMargin: 0,
                                minItems: 1,
                                maxItems: 3,
                                move: 1,
                                useCSS : false
                            });
                        });
                    </script>
						</div>

						<div class="line_2" style="margin: 20px 0px 0px;"></div>

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