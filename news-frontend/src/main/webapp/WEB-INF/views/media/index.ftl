<!DOCTYPE html>
<html>

<head>
<title>BusinessNews - Media</title>

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
                                <li>媒体</li>
                            </ul>
                        </div>
                        <div class="separator" style="height:30px;"></div>
                        
                        <h2>媒体</h2>
                        
                        <div class="line_4" style="margin:0px 0px 22px;"></div>
                        
                        <div class="block_tabs_type_3">
                            <div class="tabs">
                                <ul>
                                    <li><a href="#1" class="current">最新</a></li><!-- tab link -->
                                    <!--<li><a href="#2">流行</a></li> tab link -->
                                </ul>
                            </div>
                                        
                            <div class="tab_content">
                                <!-- tab content goes here -->
                                <div class="block_media_posts_all">
                                	<#list mideaNewsList as pic>
	                                	<article class="block_media_post_all">
	                                    	<div class="f_pic">
	                                            <a href="${rca.contextPath}/newsdetail/index.html?newsid=${pic.id}"><img style="width:132px;height:86px;" src="${BANNER_IMGSRC}${pic.mediasrc}" alt="" /><span class="hover"></span></a>
	                                        </div>
	                                        <div style="float:right;width:440px;height:86px;line-height:86px;">
		                                      	<p class="title"><a href="${rca.contextPath}/newsdetail/index.html?newsid=${pic.id}">${pic.title}</a></p>
		                                        <p class="date">${pic.createtime?date}</p>
		                                    </div>
	                                    </article>
                                    </#list>
                                </div>
                                
                                <div class="separator" style="height:12px;"></div>
                                
                                <div class="clearboth"></div>
                                
                                <#include "/common/pagination.ftl">
                            </div>
                                        
                            <div class="tab_content">
                                <!-- tab content goes here -->
                                <div class="block_media_posts_all">
                                	<#list mideaNewsList as pic>
	                                	<article class="block_media_post_all">
	                                    	<div class="f_pic">
	                                            <a href="${rca.contextPath}/newsdetail/index.html?newsid=${pic.id}"><img src="${BANNER_IMGSRC}${pic.mediasrc}" alt="" /><span class="hover"></span></a>
	                                        </div>
	                                        <div style="float:right;width:440px;height:86px;line-height:86px;">
		                                      	<p class="title"><a href="${rca.contextPath}/newsdetail/index.html?newsid=${pic.id}">${pic.title}</a></p>
		                                        <p class="date">${pic.createtime?date}</p>
	                                        </div>
	                                    </article>
                                    </#list>
                                </div>
                                
                                <div class="separator" style="height:12px;"></div>
                                
                                <div class="clearboth"></div>
                                
                                <#include "/common/pagination.ftl">
                            </div>
                                        
                            <script type="text/javascript">
                                $('.block_tabs_type_3 .tabs').tabs('.block_tabs_type_3 .tab_content', {
                                    initialIndex : 0
                                });
                            </script>
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