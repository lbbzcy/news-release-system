package com.news.handle.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.news.common.project.dto.NewsTemplateDto;
@Repository("newsTemplateDtoMapper")
public interface NewsTemplateDtoMapper {
    int deleteByPrimaryKey(String id);

    int insert(NewsTemplateDto record);

    int insertSelective(NewsTemplateDto record);

    NewsTemplateDto selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NewsTemplateDto record);

    int updateByPrimaryKeyWithBLOBs(NewsTemplateDto record);

    int updateByPrimaryKey(NewsTemplateDto record);
    
    List<NewsTemplateDto> findByName(String name);
    
    List<NewsTemplateDto> queryAllData(NewsTemplateDto newsTemplateDto);
    
    List<NewsTemplateDto> listAllTemplate();

	List<NewsTemplateDto> findNameByTmplate(ArrayList<String> templates);
}