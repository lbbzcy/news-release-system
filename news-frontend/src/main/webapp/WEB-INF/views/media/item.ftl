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
                          	<p class="title"><a href="#">${newsItem.title}</a></p>
                            
                            <div class="info">
                                <div class="date"><p>${newsItem.createtime?date}</p></div>
                                    
                            	<div class="r_part">
                                    <a href="#" class="views">${newsItem.viewnum}</a>
                                    <a href="#" class="comments">${newsItem.commentnum}</a>
                                </div>
                            </div>
                            
                            <div class="content">
                            	${newsItem.content}
                            </div>
                        </article>
                        
                        <div class="separator" style="height:6px;"></div>
                        
                        <div class="line_2" style="margin:22px 0px 30px;"></div>
                        
                        <div class="block_comments">
                        	<h3>${totalComment}条评论</h3>
                            <a href="#w_validation" class="add_new">添加评论</a>
                            <#list commentList as item>
	                            <div class="comment">
	                            	<div style="float:left;margin-right:20px;"><img style="width:50px;height:50px;" src="${item.header}" /></div>
	                                <div class="comment_wrap">
	                                    <div class="name">
	                                    	<p><a href="#">${item.username}&nbsp;&nbsp;${item.createtime?datetime}</a></p>
	                                    </div>
	                                    <div style="margin-top:10px;"></div>
	                                    <div class="content">
	                                        <p>${item.content}</p>
	                                        <#if login_user.username != item.username>
	                                    		<p><a href="javascript:void(0);" class="replayto">回复</a></p>
	                                    	</#if>
	                                    	<div class="textarea" id="sidereply" style="display:none;">
	                                    		<textarea name="recontent" cols="1" rows="1" style="width: 541px;height: 95px;line-height: 15px;margin-top: 5px;display: block;font-family: Arial, Helvetica, sans-serif;font-size: 12px;color: #989898;"></textarea>
		                                    	<a class="general_button replyconfirm" style="float:right;margin:2px;cursor:pointer;">确定</a>
		                                    	<input type="hidden" value="${item.id}">
		                                    	<a class="general_button cancel" style="float:right;margin:2px;cursor:pointer;">取消</a>
	                                    	</div>
	                                    	<#if item.children??>
	                                    		<#list item.children as child>
			                                    	<div class="line_3" style="margin-bottom: 13px"></div>
			                                    	<div style="float:left;margin-right:20px;"><img style="width:50px;height:50px;" src="${child.header}" /></div>
			                                    	<div style="margin-left:50px;" class="comment_wrap">
					                                    <div class="name">
					                                    	<p><a href="#">${child.username}&nbsp;&nbsp;${child.createtime?datetime}</a></p>
					                                    </div>
					                                    <div style="margin-top:10px;"></div>
					                                    <div class="content">
					                                        <p>${child.content}</p>
					                                        <#if login_user.username != child.username>
					                                    		<p><a href="javascript:void(0);" class="replayto">回复</a></p>
					                                    	</#if>
					                                    	<div class="textarea" id="sidereply" style="display:none;">
					                                    		<textarea name="recontent" cols="1" rows="1" style="width: 472px;height: 95px;line-height: 15px;margin-top: 5px;display: block;font-family: Arial, Helvetica, sans-serif;font-size: 12px;color: #989898;"></textarea>
						                                    	<a class="general_button replyconfirm " style="float:right;margin:2px;cursor:pointer;">确定</a>
						                                    	<input type="hidden" value="${child.id}">
						                                    	<a class="general_button cancel" style="float:right;margin:2px;cursor:pointer;">取消</a>
					                                    	</div>
					                                    	<#if child.children??>
					                                    		<#list child.children as grandson>
							                                    	<div class="line_3" style="margin-bottom: 13px"></div>
							                                    	<div style="float:left;margin-right:20px;"><img style="width:50px;height:50px;" src="${grandson.header}" /></div>
							                                    	<div style="margin-left:50px;" class="comment_wrap">
									                                    <div class="name">
									                                    	<p><a href="#">${grandson.username}&nbsp;&nbsp;${grandson.createtime?datetime}</a></p>
									                                    </div>
									                                    <div style="margin-top:10px;"></div>
									                                    <div class="content">
									                                        <p>${grandson.content}</p>
									                                    	<#if login_user.username != grandson.username>
									                                    		<p><a href="javascript:void(0);" class="replayto">回复</a></p>
									                                    	</#if>
									                                    	<div class="textarea" id="sidereply" style="display:none;">
									                                    		<textarea name="recontent" cols="1" rows="1" style="width: 402px;height: 95px;line-height: 15px;margin-top: 5px;display: block;font-family: Arial, Helvetica, sans-serif;font-size: 12px;color: #989898;"></textarea>
										                                    	<a class="general_button replyconfirm" style="float:right;margin:2px;cursor:pointer;">确定</a>
										                                    	<input type="hidden" value="${grandson.id}">
										                                    	<a class="general_button cancel" style="float:right;margin:2px;cursor:pointer;">取消</a>
									                                    	</div>
									                                    	<#if grandson.children??>
									                                    		<#list grandson.children as greatgrandson>
											                                    	<div class="line_3" style="margin-bottom: 13px"></div>
											                                    	<div style="float:left;margin-right:20px;"><img style="width:50px;height:50px;" src="${greatgrandson.header}" /></div>
											                                    	<div style="margin-left:50px;" class="comment_wrap">
													                                    <div class="name">
													                                    	<p><a href="#">${greatgrandson.username}&nbsp;&nbsp;${greatgrandson.createtime?datetime}</a></p>
													                                    </div>
													                                    <div style="margin-top:10px;"></div>
													                                    <div class="content">
													                                        <p>${greatgrandson.content}</p>
													                                    </div>
													                                </div>
													                            </#list>
												                             </#if>
									                                    </div>
									                                </div>
									                            </#list>
								                             </#if>
					                                    </div>
					                                </div>
					                            </#list>
				                             </#if>
			                                <div class="clearboth"></div>
	                                    </div>
	                                </div>
	                                <div class="clearboth"></div>
	                                <div class="line_3"></div>
	                            </div>
                            </#list>
                        	<#include "/common/pagination.ftl">
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