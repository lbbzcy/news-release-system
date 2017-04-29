<!DOCTYPE html>
<html>

<head>
<title>BusinessNews - Main News</title>

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
                                <li>新闻列表</li>
                            </ul>
                        </div>
                        <div class="separator" style="height:30px;"></div>
                        
                        <h2>新闻列表</h2>
                        
                        <p class="general_subtitle">Eestablished fact that a reader will be distracted by the readable content of a page when looking at its layout are many variations of passages have suffered alteration.</p>
                        
                        <div class="line_4" style="margin:0px 0px 22px;"></div>
                        
                        <div class="block_main_news">
                        	<#list newsDetailList as item>
	                            <article class="block_news_post">
	                                <p class="title"><a href="${rca.contextPath}/newsdetail/index.html">${item.title}</a></p>
	                                <p class="text">${item.brief}</p>
	                                <p class="category"><a href="#">${item.typename}</a></p>
	                                <div class="info">
	                                	<div class="date"><p>${item.createtime?date}</p></div>
	                                    <a href="#" class="views">${item.viewnum}</a>
	                                    <div class="clearboth"></div>
	                                </div>
	                            </article>
                            </#list>
                        </div>
                        
                        <div class="line_2" style="margin:8px 0px 25px;"></div>
                        
                        <#include "/common/pagination.ftl">
                        
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