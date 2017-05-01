<!DOCTYPE html>
<html>

<head>
<title>BusinessNews - Business News - News Name</title>

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
                            	<li><a href="${rca.contextPath}/main/index.html">主页</a></li>
                                <li><a href="${rca.contextPath}/newstype/index.html?typeid=${newsItem.typeid}&typename=${newsItem.typename}">${newsItem.typename}</a></li>
                                <li>${newsItem.title}</li>
                            </ul>
                        </div>
                        <div class="separator" style="height:30px;"></div>
                        
                        <article class="block_single_news">
                          <p class="title"><a href="#">${newsItem.title}</a></p>
                            <p class="subtitle">${newsItem.brief}</p>
                            
                            <div class="info">
                                <div class="date"><p>${newsItem.createtime?date}</p></div>
                                <div class="author"><p>By: <a href="#">${newsItem.creator}</a></p></div>
                                    
                            	<div class="r_part">
                                	<div class="category">
                                		<p>
                                			<a href="${rca.contextPath}/newstype/index.html?typeid=${newsItem.typeid}&typename=${newsItem.typename}">${newsItem.typename}</a>
                                		</p>
                                	</div>
                                    <a href="#" class="views">${newsItem.viewnum}</a>
                                    <a href="#" class="comments">${newsItem.commentnum}</a>
                                </div>
                            </div>
                            
                            <div class="content">
                            	${newsItem.content}
                            </div>
                        </article>
                        
                        <div class="separator" style="height:4px;"></div>
                        
                        <div class="block_post_tags">
                        	<p>标签: <a href="#">${newsItem.typename},</a><a href="#">${newsItem.templatename}</a></p>
                        </div>
                        
                        <div class="line_2" style="margin:22px 0px 29px;"></div>
                        
                        <div class="block_related_posts">
                        	<h3>相关新闻</h3>
                            
                            <div class="block_main_news">
                            	<#list relatedNews as item>
	                            	<article class="block_news_post">
	                                  <p class="category"><a href="#">${item.typename}</a></p>
	                                    <p class="title"><a href="#">${item.title}</a></p>
	                                    <div class="info">
	                                        <div class="date"><p>${item.createtime?date}</p></div>
	                                        <a href="#" class="views">${item.viewnum}</a>
	                                        
	                                        <div class="clearboth"></div>
	                                    </div>
	                                </article>
                                </#list>
                                
                            	<div class="clearboth"></div>
                            </div>
                        </div>
                        
                        <div class="line_2" style="margin:5px 0px 30px;"></div>
                        
                        <div class="block_comments_type_2">
                        	<h3>3 条评论</h3>
                            <a href="#" class="add_new">Add new comment</a>
                            
                            <div class="comment">
                            	<div class="userpic"><a href="#"><img src="${rca.contextPath}/images/ava_default_1.jpg" alt="" /></a></div>
                                
                                <div class="comment_wrap">
                                    <div class="name"><p><a href="#">John Doe</a></p></div>
                                    <div class="date"><p>Febr 16, 2012 at 4:43 pm</p></div>
                                    <div class="content">
                                        <p>Established fact that a reader will be distracted by the readable content of a page.</p>
                                    </div>
                                </div>
                                <div class="clearboth"></div>
                                <div class="line_3"></div>
                            </div>
                            
                            <div class="comment">
                            	<div class="userpic"><a href="#"><img src="${rca.contextPath}/images/ava_default_1.jpg" alt="" /></a></div>
                                
                                <div class="comment_wrap">
                                    <div class="name"><p><a href="#">Sara Jonson</a></p></div>
                                    <div class="date"><p>Febr 16, 2012 at 4:43 pm</p></div>
                                    <div class="content">
                                        <p>Established fact that a reader will be distracted by the readable content of a page. When looking at its layout. The point of using is that it has a more-or-less normal distribution of letters.</p>
                                    </div>
                                </div>
                                <div class="clearboth"></div>
                                <div class="line_3"></div>
                            </div>
                            
                            <div class="comment">
                            	<div class="userpic"><a href="#"><img src="${rca.contextPath}/images/ava_default_1.jpg" alt="" /></a></div>
                                
                                <div class="comment_wrap">
                                    <div class="name"><p><a href="#">Mark Defo</a></p></div>
                                    <div class="date"><p>Febr 16, 2012 at 4:43 pm</p></div>
                                    <div class="content">
                                        <p>Page when looking at its layout. The point of usinghas a more-or-less normal distribution.</p>
                                    </div>
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