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
                        	<#if newsItem.mediasrc??>
	                        	<div style="margin-bottom:20px;">
	                        		<img style="width:610px;height:292px;" src="${BANNER_IMGSRC}${newsItem.mediasrc}" />
	                        	</div>
                        	</#if>
                          <p class="title"><a href="#">${newsItem.title}</a></p>
                            <p class="subtitle">${newsItem.brief}</p>
                            
                            <div class="info">
                                <div class="date"><p>${newsItem.createtime?date}</p></div>
                                <div class="author"><p>作者: <a href="#">${newsItem.creator}</a></p></div>
                                    
                            	<div class="r_part">
                                	<div class="category">
                                		<p>
                                			<a href="${rca.contextPath}/newstype/index.html?typeid=${newsItem.typeid}&typename=${newsItem.typename}">${newsItem.typename}</a>
                                		</p>
                                	</div>
                                    <a href="#" class="views">${newsItem.viewnum}</a>
                                    <a href="#" class="comments">${totalComment}</a>
                                </div>
                            </div>
                            
                            <div class="content">
                            	${newsItem.content}
                            </div>
                        </article>
                        
                        <div class="separator" style="height:4px;"></div>
                        
                        <div class="block_post_tags">
                        	<p>标签: <a href="#">${newsItem.typename},</a>
                        	<a href="#">${newsItem.templatename}</a>
                        	<span id="collect">
                        		<#if isCollect == true>
                        			<i id="collect-success" class="fa fa-star-o collect-i" aria-hidden="true"></i>
                        		<#else>
                        			<i id="collect-success" class="fa fa-star-o" aria-hidden="true"></i>
                        		</#if>收藏
                        		
                        	</span>
                        	</p>
                        </div>
                        
                        <div class="line_2" style="margin:22px 0px 29px;"></div>
                        
                        <div class="block_related_posts">
                        	<h3>相关新闻</h3>
                            <#if relatedNews?? && (relatedNews?size>0)>
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
	                         <#else>
	                         	暂无相关新闻
	                         </#if>
                        </div>
                        
                        <div class="line_2" style="margin:5px 0px 30px;"></div>
                        <#if commentList?? && (commentList?size>0)>
	                        <div class="block_comments_type_2">
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
                        	<#else>
                        		暂时没有评论
                        	</#if>
                        <div class="separator" style="height:30px;"></div>
                        
                        <div class="block_leave_reply">
                        	<h3>发表评论</h3>
                        	<form id="w_validation" />
                                <p>评论内容</p>
                                <div class="textarea"><textarea name="recontent" id="recontent" cols="1" rows="1"></textarea></div>
                               	<input type="hidden" name="newsid" value="${newsItem.id}" />
                                <a class="general_button" style="cursor:pointer;" onclick="replay();">发表评论</a>
                            </form>
                        </div>
                        <script type="text/javascript">
                        	$(function(){
                        		//收藏功能
                        		$('#collect').click(function(){
                        			var username = "${Session["login_user"].username}";
                        			var isCollect = $('#collect-success').hasClass("collect-i");
                        			console.log(isCollect);
                        			console.log(username);
                        			if(validata(username)){
                        				layer.msg('用户未登录，登录后才可以收藏！', {
											time : 2000,
											offset : '100px'
										});
                        			}else if(isCollect){
                        				layer.msg('已经收藏过！', {
											time : 2000,
											offset : '100px'
										});
                        			}else{
	                        			var newsid = "${newsItem.id}";
	                        			console.log(newsid);
	                        			$.ajax({
	                        				type:"post",
	                        				url:"${rca.contextPath}/personal/collect.html",
	                        				data:{
	                        					newsid:newsid
	                        				},
	                        				dataType:"json",
	                        				success:function(data){
	                        					console.log(data);
	                        					if("success" == data){
	                        						//收藏成功
	                        						$('#collect-success').addClass("collect-i");
	                        						layer.msg('收藏成功', {
														time : 2000,
														offset : '100px'
													});
	                        					}else{
	                        						//收藏失败
	                        						layer.msg('收藏失败', {
														time : 2000,
														offset : '100px'
													});
	                        					}
	                        				}
	                        			});
	                        		}
                        		});
                        		//回复和取消显示
                        		$('.replayto').click(function(){
                        			$(this).parent().next().css("display","block");
                        		});
                        		$('.cancel').click(function(){
                        			$(this).parent().css("display","none");
                        		});
                        		//消息评论
                        		$('.replyconfirm').click(function(){
                        			var replycontent = $(this).prev().val();
                        			var replayid = $(this).next().val();
                        			var newsid = "${newsItem.id}";
                        			if(null == replycontent || "" == replycontent){
                        				layer.msg("请填写评论内容",{
                							icon:1
                						});
                        			}else{
                        				$.ajax({
                        					type:"post",
                        					url:"${rca.contextPath}/comment/replay.html",
                        					data:{
                        						recontent:replycontent,
                        						replayid:replayid,
                        						newsid:newsid
                        					},
                        					dataType:"json",
                        					success:function(data){
                        						if(data=="success"){
	                        						layer.msg("评论成功",{
	                									icon:1
	                								});
	                								setTimeout(function(){
	                									window.location.reload();
	                								},1000);
	                							}
                        					},
                        					error:function(XMLHttpRequest, textStatus, errorThrown){
												console.log(XMLHttpRequest.readyState + "," + XMLHttpRequest.status+ "," + XMLHttpRequest.responseText);
											}
                        				});
                        			}
                        		});
                        	});
                        	//消息回复
                        	function replay(){
                        		var result = true;
                        		$.ajax({
                        			type:"get",
                        			url:"${rca.contextPath}/comment/checkIsUserLogin.html",
                        			dataType:"json",
                        			async:false,
                        			success:function(data){
                        				if(data!="success"){
                        				console.log("还没有登录");
                        					layer.msg("请先登录，登录后才可以发表评论！", {
												icon : 1
											});
											result = false;
                        				}
                        			},
                        			error:function(XMLHttpRequest, textStatus, errorThrown){
										console.log(XMLHttpRequest.readyState + "," + XMLHttpRequest.status+ "," + XMLHttpRequest.responseText);
									}
                        		});
                        		if(result){
	                        		var recontent = $("#recontent").val();
	                        		var newsid = "${newsItem.id}";
	            					if(null==recontent || "" == recontent){
	            						layer.msg("请填写评论内容",{
	            							icon:1
	            						});
	            					}else{
	                					$.ajax({
                        					type:"post",
                        					url:"${rca.contextPath}/comment/replay.html",
                        					data:{
                        						recontent:recontent,
                        						newsid:newsid
                        					},
                        					dataType:"json",
                        					success:function(data){
                        						if(data=="success"){
	                        						layer.msg("评论成功",{
	                									icon:1
	                								});
	                								setTimeout(function(){
	                									window.location.reload();
	                								},1000);
	                							}
                        					},
                        					error:function(XMLHttpRequest, textStatus, errorThrown){
												console.log(XMLHttpRequest.readyState + "," + XMLHttpRequest.status+ "," + XMLHttpRequest.responseText);
											}
                        				});
	            					}
	            				}
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