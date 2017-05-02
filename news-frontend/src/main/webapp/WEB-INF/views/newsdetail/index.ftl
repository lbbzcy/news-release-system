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
                        	<h3>${totalComment}条评论</h3>
                            <a href="#w_validation" class="add_new">添加评论</a>
                            <#list commentList as item>
	                            <div class="comment">
	                                <div class="comment_wrap">
	                                    <div class="name"><p><a href="#">${item.username}</a></p></div>
	                                    <div class="date">
	                                    	<p>${item.createtime?datetime}</p>
	                                    </div>
	                                    <div class="content">
	                                        <p>${item.content}</p>
	                                    	<p><a href="javascript:void(0);" class="replayto">回复</a></p>
	                                    	<div class="textarea" id="sidereply" style="display:none;">
	                                    		<textarea name="recontent" id="recontent" cols="1" rows="1" style="width: 347px;height: 95px;line-height: 15px;margin-top: 5px;display: block;background-color: transparent;font-family: Arial, Helvetica, sans-serif;font-size: 12px;color: #989898;"></textarea>
		                                    	<a class="general_button" style="float:right;margin:2px;" onclick="replay();">确定</a>
		                                    	<a class="general_button cancel" style="float:right;margin:2px;">取消</a>
	                                    	</div>
	                                    </div>
	                                </div>
	                                <div class="clearboth"></div>
	                                <div class="line_3"></div>
	                            </div>
                            </#list>
                            <div style="margin:22px 0px 29px;"></div>
                            <#include "/common/pagination.ftl">
                        </div>
                        
                        <div class="separator" style="height:30px;"></div>
                        
                        <div class="block_leave_reply">
                        	<h3>发表评论</h3>
                        	<form id="w_validation" action="${rca.contextPath}/comment/replay.html" />
                                <p>评论内容</p>
                                <div class="textarea"><textarea name="recontent" id="recontent" cols="1" rows="1"></textarea></div>
                               	<input type="hidden" name="newsid" value="${newsItem.id}" />
                                <a class="general_button" onclick="replay();">发表评论</a>
                            </form>
                        </div>
                        <script type="text/javascript">
                        	$(function(){
                        		$('.replayto').click(function(){
                        			$(this).parent().next().css("display","block");
                        		});
                        		$('.cancel').click(function(){
                        			$(this).parent().css("display","none");
                        		});
                        	});
                        	function replay(){
                        		$.ajax({
                        			type:"get",
                        			url:"${rca.contextPath}/comment/checkIsUserLogin.html",
                        			success:function(data){
                        				if(data=="success"){
                        					console.log("已经登录");
                        					var recontent = $("#recontent").val();
                        					if(null==recontent || "" == recontent){
                        						layer.msg("请填写评论内容",{
                        							icon:1
                        						});
                        					}else{
	                        					console.log(recontent);
	                        					$('#w_validation').submit();
                        					}
                        				}else{
                        					console.log("还没有登录");
                        					layer.msg("请先登录，登录后才可以发表评论！", {
												icon : 1
											});
                        				}
                        			},
                        			error:function(XMLHttpRequest, textStatus, errorThrown){
										console.log(XMLHttpRequest.readyState + "," + XMLHttpRequest.status+ "," + XMLHttpRequest.responseText);
									}
                        		});
                        	}
                        </script>
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