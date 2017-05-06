package com.news.common.core.utils;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.news.common.project.dto.NewsDetailDto;

public class SolrUtils {
	@SuppressWarnings({ "deprecation", "resource" })
	public static List<NewsDetailDto> querySolr(String queryContent) throws Exception{
    	List<NewsDetailDto> result = null;
        HttpSolrClient solrServer = new HttpSolrClient("http://localhost:8090/solr/cores/");  
        SolrQuery query = new SolrQuery();  
        //下面设置solr查询参数
        query.set("q",queryContent);
        //参数fq, 给query增加过滤查询条件  
        query.addFilterQuery("isdel:0");
        //query.addFilterQuery("id:[0 TO 9]");//id为0-4  
        //给query增加布尔过滤条件  
        //query.addFilterQuery("description:演员");  //description字段中含有“演员”两字的数据
        //参数df,给query设置默认搜索域  
        query.set("df", "newscontent");  
        //参数sort,设置返回结果的排序规则  
        query.setSort("updatetime",SolrQuery.ORDER.desc);
        //设置分页参数  
        query.setStart(0);  
        query.setRows(8);//每一页多少值  
        //参数hl,设置高亮  
        query.setHighlight(true);  
        //设置高亮的字段  
        query.addHighlightField("newstitle");  
        //设置高亮的样式  
        query.setHighlightSimplePre("<font color='red'>");  
        query.setHighlightSimplePost("</font>"); 
        //获取查询结果
        QueryResponse response = solrServer.query(query);  
        //得到实体对象
        result = response.getBeans(NewsDetailDto.class);
        return result;
    }
}
