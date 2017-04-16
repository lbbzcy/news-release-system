package com.news.backend.util.exl;


public class ExcelExportModel
{
    /**
     * 标识列
     */
    private String id;
    
    /**
     * 工作薄名称
     */
    private String sheetName;
    
    /**
     * 标题，如果此值为空不追加标题
     */
    private String title;
    
    public ExcelExportModel(String id,String sheetName,String title){
       this.id = id;
       this.sheetName = sheetName;
       this.title = title;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getSheetName()
    {
        return sheetName;
    }

    public void setSheetName(String sheetName)
    {
        this.sheetName = sheetName;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
