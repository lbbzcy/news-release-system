<!DOCTYPE html>
<html lang="zh-CN">

<head>
<title>BusinessNews - Culture News</title>

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
                	<div style="width:100%;" class="main_content">
                    	<div class="block_breadcrumbs">
                        	<div class="text"><p>您现在的位置:</p></div>
                            
                            <ul>
                            	<li><a href="index.html">主页</a></li>
                                <li>搜索结果</li>
                            </ul>
                        </div>
                        <div class="separator" style="height:30px;"></div>
                        
                        <h2>搜索结果</h2>
                        
                        <div class="line_4" style="margin:0px 0px 22px;"></div>
                        <#if pageData.rows?size gte 1 >
	                        <div class="block_main_news">
								<#list pageData.rows as item>                            
		                            <article class="block_news_post">
		                                <p class="title"><a href="${rca.contextPath}/newsdetail/index.html?newsid=${item.id}">${item.title}</a></p>
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
	                    <#else>
	                    	没有搜索结果
                        </#if>
                        
                    </div>
                    
                    <div class="clearboth"></div>
                </div>
            </div>
        </div>
    	<!-- CONTENT END -->
        <#include "/common/footer.ftl">
    </div>
    <#include "/common/popup.ftl">
</body>

</html>