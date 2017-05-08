<!DOCTYPE html>
<html lang="zh-CN">

<head>
<title>BusinessNews - Registration</title>

<meta name="keywords" content="" />
<meta name="description" content="" />

<meta charset="utf-8" />
<meta name="viewport" content="width=device-width" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${rca.contextPath}/css/personal.css" />
<#include "/common/allJsAndCSS.ftl">
</head>
<body>
	<div class="wrapper sticky_footer">
		<#include "/common/header.ftl"> <!-- CONTENT BEGIN -->
		<div id="content">
			<div id="wrapper">
			    <div riot-tag="yheader" class="yheader">
			    	<a href="/c/user/2757630490/">
			    		<img class="bg-header" alt="头像" src="http://s3.pstatp.com/site/tt_mfsroot/pc_img/bg_profile.png">
			    	</a> 
			    <div> 
			    <a ga_event="user_head_click" href="/c/user/2757630490/">
			    	<img alt="作者头像" class="avatar" src="http://tp3.sinaimg.cn/3791496330/50/5673956468/1">
			    </a> 
			    <ul> 
			    	<li class="title"> 
			    		<a ga_event="user_head_click" href="/c/user/2757630490/"> 
			    			<span class="name">随雨而安Q</span>   
			    		</a> 
			    	</li> 
			    	<li class="des">
			    		<a ga_event="user_head_click" href="/c/user/2757630490/"></a>
			    	</li> 
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
				      	<div riot-tag="relatedFeed" ban="false" page_type="0" empty="true">
					      	<div class="relatedFeed"> 
						      	<ul> 
						      		<li class="empty">暂无内容</li>
						      		<!--<li class="item">
										<div class="item-inner y-box">
											<div class="normal rbox ">
												<div class="rbox-inner">
													<div class="title-box" ga_event="article_title_click">
														<a class="link title" target="_blank"
															href="/item/6393113329958650369/">他是中国第一个入狱的童星，星途明朗却无奈打得一手烂牌</a>
													</div>
													<div class="y-box footer">
														<div class="y-left">
															<a class="lbtn comment" target="_blank"
																ga_event="article_comment_click"
																href="/item/6393113329958650369//#comment_area">&nbsp;25评论&nbsp;</a>
															<span class="lbtn">⋅&nbsp;2017-03-03 11:44</span>
														</div>
														<div class="y-right">
															<span class="dislike" data-groupid="6393108539070267650"
																ga_event="article_dislike_click"> 取消收藏 </span>
														</div>
													</div>
												</div>
											</div>
										</div>
									</li>-->
						      	</ul> 
						    </div>
						 </div>   
						</div>
					</div>
			    </div>
			    <div class="right">
			      <div riot-tag="statistics">
			      	<dl class="statistics"> 
			      		<dt> 
			      			<a ga_event="nav_user_list" href="/c/user/relation/2757630490/?tab=following"> 
				      			<h3 riot-tag="number" number="5">
				      				<em class="y-number"><i>5</i></em>
				      			</h3> 
				      			<p>关注</p> 
				      		</a> 
				      	</dt> 
				      	<dd> 
				      		<a ga_event="nav_user_list" href="/c/user/relation/2757630490/?tab=followed"> 
				      			<h3 riot-tag="number" number="0"><em class="y-number"><i>0</i></em></h3> 
				      			<p>粉丝</p> 
				      		</a> 
				      	</dd> 
				      </dl>
				     </div>
			       <div riot-tag="recent"></div>
			    </div>
		</div>
		<#include "/common/footer.ftl">
	</div>
	<#include "/common/popup.ftl">
</body>

</html>