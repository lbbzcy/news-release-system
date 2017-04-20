<!DOCTYPE html>
<html>

<head>
<title>BusinessNews - Media - Media Name</title>

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
                    	<div class="block_breadcrumbs">
                        	<div class="text"><p>您现在的位置:</p></div>
                            
                            <ul>
                            	<li><a href="index.html">主页</a></li>
                                <li><a href="media.html">媒体</a></li>
                                <li>媒体名称</li>
                            </ul>
                        </div>
                        <div class="separator" style="height:30px;"></div>
                        
                        <article class="block_media_item">
                        	<div class="f_item">
                            	<div id="media_item_slider" class="media_item_slider flexslider">
                                	<ul class="slides">
                                    	<li>
                                        	<img src="${rca.contextPath}/images/pic_media_item_1.jpg" alt="" />
                                            <div class="caption"><p><b>Photo1.</b> Many desktop publishing packages and web page.</p></div>
                                        </li>
                                        <li>
                                        	<img src="${rca.contextPath}/images/pic_media_item_1.jpg" alt="" />
                                            <div class="caption"><p><b>Photo2.</b> Many desktop publishing packages and web page.</p></div>
                                        </li>
                                        <li>
                                        	<img src="${rca.contextPath}/images/pic_media_item_3.jpg" alt="" />
                                            <div class="caption"><p><b>Photo3.</b> Many desktop publishing packages and web page.</p></div>
                                        </li>
                                        <li>
                                        	<img src="${rca.contextPath}/images/pic_media_item_1.jpg" alt="" />
                                            <div class="caption"><p><b>Photo4.</b> Many desktop publishing packages and web page.</p></div>
                                        </li>
                                        <li>
                                        	<img src="${rca.contextPath}/images/pic_media_item_3.jpg" alt="" />
                                            <div class="caption"><p><b>Photo5.</b> Many desktop publishing packages and web page.</p></div>
                                        </li>
                                        <li>
                                        	<img src="${rca.contextPath}/images/pic_media_item_1.jpg" alt="" />
                                            <div class="caption"><p><b>Photo6.</b> Many desktop publishing packages and web page.</p></div>
                                        </li>
                                        <li>
                                        	<img src="${rca.contextPath}/images/pic_media_item_1.jpg" alt="" />
                                            <div class="caption"><p><b>Photo7.</b> Many desktop publishing packages and web page.</p></div>
                                        </li>
                                        <li>
                                        	<img src="${rca.contextPath}/images/pic_media_item_1.jpg" alt="" />
                                            <div class="caption"><p><b>Photo8.</b> Many desktop publishing packages and web page.</p></div>
                                        </li>
                                        <li>
                                        	<img src="${rca.contextPath}/images/pic_media_item_3.jpg" alt="" />
                                            <div class="caption"><p><b>Photo9.</b> Many desktop publishing packages and web page.</p></div>
                                        </li>
                                        <li>
                                        	<img src="${rca.contextPath}/images/pic_media_item_1.jpg" alt="" />
                                            <div class="caption"><p><b>Photo10.</b> Many desktop publishing packages and web page.</p></div>
                                        </li>
                                        <li>
                                        	<img src="${rca.contextPath}/images/pic_media_item_3.jpg" alt="" />
                                            <div class="caption"><p><b>Photo11.</b> Many desktop publishing packages and web page.</p></div>
                                        </li>
                                        <li>
                                        	<img src="${rca.contextPath}/images/pic_media_item_1.jpg" alt="" />
                                            <div class="caption"><p><b>Photo12.</b> Many desktop publishing packages and web page.</p></div>
                                        </li>
                                    </ul>
                                </div>
                                
                                <div id="media_item_navigation" class="media_item_navigation flexslider">
                                	<ul class="slides">
                                    	<li><img src="${rca.contextPath}/images/pic_media_item_1_sm.jpg" alt="" /><span class="current"></span></li>
                                        <li><img src="${rca.contextPath}/images/pic_media_item_2_sm.jpg" alt="" /><span class="current"></span></li>
                                        <li><img src="${rca.contextPath}/images/pic_media_item_3_sm.jpg" alt="" /><span class="current"></span></li>
                                        <li><img src="${rca.contextPath}/images/pic_media_item_4_sm.jpg" alt="" /><span class="current"></span></li>
                                        <li><img src="${rca.contextPath}/images/pic_media_item_5_sm.jpg" alt="" /><span class="current"></span></li>
                                        <li><img src="${rca.contextPath}/images/pic_media_item_6_sm.jpg" alt="" /><span class="current"></span></li>
                                        <li><img src="${rca.contextPath}/images/pic_media_item_1_sm.jpg" alt="" /><span class="current"></span></li>
                                        <li><img src="${rca.contextPath}/images/pic_media_item_2_sm.jpg" alt="" /><span class="current"></span></li>
                                        <li><img src="${rca.contextPath}/images/pic_media_item_3_sm.jpg" alt="" /><span class="current"></span></li>
                                        <li><img src="${rca.contextPath}/images/pic_media_item_4_sm.jpg" alt="" /><span class="current"></span></li>
                                        <li><img src="${rca.contextPath}/images/pic_media_item_5_sm.jpg" alt="" /><span class="current"></span></li>
                                        <li><img src="${rca.contextPath}/images/pic_media_item_6_sm.jpg" alt="" /><span class="current"></span></li>
                                    </ul>
                                </div>
                                
                                <script type="text/javascript">
									$(function() {
										$('#media_item_navigation').flexslider({
											animation : 'slide',
											controlNav : false,
											directionNav : false,
											animationLoop : false,
											slideshow : false,
											itemWidth : 91,
											itemMargin : 4,
											asNavFor : '#media_item_slider',
											useCSS : false
										});
										$('#media_item_slider').flexslider({
											animation : 'fade',
											controlNav : false,
											animationLoop : false,
											slideshow : false,
											sync : '#media_item_navigation'
										});
									});
								</script>
                            </div>
                          	<p class="title"><a href="#">Randomised words which don't look even slightly.</a></p>
                            
                            <div class="info">
                                <div class="date"><p>15 July, 2012</p></div>
                                    
                            	<div class="r_part">
                                    <a href="#" class="views">650</a>
                                    <a href="#" class="comments">25</a>
                                </div>
                            </div>
                            
                            <div class="content">
                            	<p>There are many <b>variations of passages</b> <a href="#" class="lnk_blue"><b>of available, but the majority have suffered</b></a> alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text.</p>
                            	<p>All the generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over words, combined with a <b>handful of model sentence</b> structures, to generate which looks reasonable.</p>
                            	<p>The generated Lorem Ipsum is therefore always free from repetition, injected humour words etc. Available, but the majority have <b>suffered alteration</b>.By injected humour, or randomised words which don't look.</p>
                            </div>
                        </article>
                        
                        <div class="separator" style="height:6px;"></div>
                        
                        <div class="line_2" style="margin:22px 0px 30px;"></div>
                        
                        <div class="block_comments">
                        	<h3>2 条评论</h3>
                            
                            <div class="comment">
                            	<div class="userpic"><a href="about_author.html"><img src="${rca.contextPath}/images/ava_default_1.jpg" alt="" /></a></div>
                                <div class="content">
                                	<p class="name"><a href="about_author.html">John Doe</a></p>
                                    <p class="info"><span class="date">Febr 16, 2012 at 4:43 pm</span><a href="#" class="control">Reply</a></p>
                                    <p class="text">Established fact that a reader will be distracted by the readable content of a page. When looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using.</p>
                                </div>
                                <div class="clearboth"></div>
                                <div class="line_3"></div>
                            </div>
                            
                            <div class="comment">
                            	<div class="userpic"><a href="about_author.html"><img src="${rca.contextPath}/images/ava_default_1.jpg" alt="" /></a></div>
                                <div class="content">
                                	<p class="name"><a href="about_author.html">Sara Gordon</a></p>
                                    <p class="info"><span class="date">Febr 16, 2012 at 4:43 pm</span><a href="#" class="control">Reply</a></p>
                                    <p class="text">Distracted by the readable content of a page. When looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed.</p>
                                </div>
                                <div class="clearboth"></div>
                                <div class="line_3"></div>
                            </div>
                            
                        </div>
                        
                        <div class="separator" style="height:30px;"></div>
                        
                        <div class="block_leave_reply">
                        	<h3>发表评论</h3>
                            
                        	<form class="w_validation" action="#" />
                                <p>评论内容</p>
                                <div class="textarea"><textarea cols="1" rows="1"></textarea></div>
                                
                                <input type="submit" class="general_button" value="发表评论" />
                            </form>
                        </div>
                        
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