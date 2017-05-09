<!DOCTYPE html>
<html lang="zh-CN">

<head>
<title>BusinessNews - Registration</title>

<meta name="keywords" content="" />
<meta name="description" content="" />

<meta charset="utf-8" />
<meta name="viewport" content="width=device-width" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "/common/allJsAndCSS.ftl">
<link rel="stylesheet" href="${rca.contextPath}/css/personal.css" />
<link rel="stylesheet" type="text/css" href="${rca.contextPath}/css/font-awesome.4.6.0.css">
<link rel="stylesheet" href="${rca.contextPath}/css/amazeui.min.css">
<link rel="stylesheet" href="${rca.contextPath}/css/amazeui.cropper.css">
<link rel="stylesheet" href="${rca.contextPath}/css/custom_up_img.css">
<script src="${rca.contextPath}/js/amazeui.min.js" charset="utf-8"></script>
<script src="${rca.contextPath}/js/cropper.min.js" charset="utf-8"></script>
<script src="${rca.contextPath}/js/custom_up_img.js" charset="utf-8"></script>
</head>
<body>
	<div class="wrapper sticky_footer">
		<#include "/common/header.ftl"> <!-- CONTENT BEGIN -->
		<div id="content">
			<div id="wrapper">
				<div riot-tag="yheader" class="yheader">
					<a href="${rca.contextPath}/main/index.html"> 
						<img class="bg-header " alt="头像"
						src="${rca.contextPath}/images/bg_profile.png">
					</a>
					<div>
						<a class="up-img-cover"  id="up-img-touch" ga_event="user_head_click" data-am-popover="{content: '点击上传', trigger: 'hover focus'}"> 
						<img alt="作者头像" class="avatar" src="${login_user.header}" >
						</a>
						<ul>
							<li class="title"><a ga_event="user_head_click"
								href="/c/user/2757630490/"> <span class="name">${login_user.nickname}</span>
							</a></li>
							<li class="des"><a ga_event="user_head_click"
								href="/c/user/2757630490/"></a></li>
						</ul>
					</div>
				</div>
				<div class="left">
					<div riot-tag="tab">
						<ul class="tab tab-0">
							<li code="b" idx="0" class="active">收藏</li>
							<!--<li code="c" idx="1" class="">收藏</li>riot placeholder-->
						</ul>
					</div>
					<div riot-tag="feedBox">
						<div class="feedBox" name="feedbox">
							<div class="tt-declare">以下内容由今日头条提供</div>
							<div class="tt-related-title">相关推荐</div>
							<div riot-tag="relatedFeed" ban="false" page_type="0"
								empty="true">
								<div class="relatedFeed">
									<ul>
										<#if collectList??&&(collectList?size>0)>
											<#list collectList as item>
												<li class="item">
												<div class="item-inner y-box">
													<div class="normal rbox ">
														<div class="rbox-inner">
															<div class="title-box" ga_event="article_title_click">
																<a class="link title" target="_blank"
																	href="${rca.contextPath}/newsdetail/index.html?newsid=${item.id}">${item.title}</a>
															</div>
															<div class="y-box footer">
																<div class="y-left">
																	<span class="lbtn comment">&nbsp;${item.commentnum}评论&nbsp;</span>
																	<span class="lbtn">⋅&nbsp;${item.createtime?datetime}</span>
																</div>
																<div class="y-right">
																	<span class="dislike" onclick="cancelCollect('${item.id}');">取消收藏 </span>
																</div>
															</div>
														</div>
													</div>
												</div>
											</li>
										</#list>
										<#else>
											<li class="empty">暂无内容</li>
										</#if>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<script type="text/javascript">
					//取消收藏功能
					function cancelCollect(newsid){
						$.ajax({
							type:"post",
							url:"${rca.contextPath}/personal/cancelCollect.html",
							data:{
								newsid:newsid
							},
							dataType:"json",
							success:function(data){
								if(data=="success"){
									layer.msg('取消收藏成功', {
										time : 2000,
										offset : '100px'
									});
									setTimeout(function(){
										window.location.reload();
									},2000);
								}else{
									layer.msg('取消收藏失败，稍后再试！', {
										time : 2000,
										offset : '100px'
									});
								}
							}
						});
					}
				</script>
				<div class="right">
					<div riot-tag="statistics">
						<dl class="statistics">
							<dt><p>用户名:</p></dt>
							<dd><p>${Session["login_user"].username}</p></dd>
						</dl>
						<dl class="statistics">
							<dt><p>昵称:</p></dt>
							<dd><p>${Session["login_user"].nickname}</p></dd>
						</dl>
						<dl class="statistics">
							<dt><p>手机号码:</p></dt>
							<dd><p>${Session["login_user"].mobile}</p></dd>
						</dl>
						<dl class="statistics">
							<dt><p>邮箱:</p></dt>
							<dd><p>${Session["login_user"].email}</p></dd>
						</dl>
					</div>
					<div riot-tag="recent"></div>
				</div>
			</div>
			<div class="clearboth"></div>
			<!--图片上传框-->
				<div class="am-modal am-modal-no-btn up-frame-bj " tabindex="-1" id="doc-modal-1">
				  <div class="am-modal-dialog up-frame-parent up-frame-radius">
					<div class="am-modal-hd up-frame-header">
					   <label>修改头像</label>
					  <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
					</div>
					<div class="am-modal-bd  up-frame-body">
					  <div class="am-g am-fl">
						<div class="am-form-group am-form-file">
						  <div class="am-fl">
							<button type="button" class="am-btn am-btn-default am-btn-sm">
							  <i class="am-icon-cloud-upload"></i> 选择要上传的文件</button>
						  </div>
						  <input type="file" id="inputImage">
						</div>
					  </div>
					  <div class="am-g am-fl" >
						<div class="up-pre-before up-frame-radius">
							<img alt="" src="" id="image" >
						</div>
						<div class="up-pre-after up-frame-radius">
						</div>
					  </div>
					  <div class="am-g am-fl">
						<div class="up-control-btns">
							<span class="am-icon-rotate-left"  onclick="rotateimgleft()"></span>
							<span class="am-icon-rotate-right" onclick="rotateimgright()"></span>
							<span class="am-icon-check" id="up-btn-ok" url="${rca.contextPath}/personal/upload.html"></span>
						</div>
					  </div>
					  
					</div>
				  </div>
				</div>
				
				<!--加载框-->
				<div class="am-modal am-modal-loading am-modal-no-btn" tabindex="-1" id="my-modal-loading">
				  <div class="am-modal-dialog">
					<div class="am-modal-hd">正在上传...</div>
					<div class="am-modal-bd">
					  <span class="am-icon-spinner am-icon-spin"></span>
					</div>
				  </div>
				</div>
				
				<!--警告框-->
				<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
				  <div class="am-modal-dialog">
					<div class="am-modal-hd">信息</div>
					<div class="am-modal-bd"  id="alert_content">
							  成功了
					</div>
					<div class="am-modal-footer">
					  <span class="am-modal-btn">确定</span>
					</div>
				  </div>
				</div>
			<#include "/common/footer.ftl">
		</div>
		<#include "/common/popup.ftl">
</body>

</html>