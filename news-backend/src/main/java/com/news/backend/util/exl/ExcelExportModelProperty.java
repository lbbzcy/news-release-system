package com.news.backend.util.exl;


public class ExcelExportModelProperty
{
    private String name;                    //字段属性名
    private String excelTitleName;          //Excel标题名字
    private String dataType;                //数据类型
    private String align;                   //对齐方式
    private String format;                  //日期格式化字符串
    private Object defaultValue;            //默认值
    private int columnWidth;                //列宽
    private boolean autoLine;               //内容超出列宽是否自动换行
    
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getExcelTitleName()
    {
        return excelTitleName;
    }
    public void setExcelTitleName(String excelTitleName)
    {
        this.excelTitleName = excelTitleName;
    }
    public String getDataType()
    {
        return dataType;
    }
    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }
    public String getAlign()
    {
        return align;
    }
    public void setAlign(String align)
    {
        this.align = align;
    }
    public String getFormat()
    {
        return format;
    }
    public void setFormat(String format)
    {
        this.format = format;
    }
    public Object getDefaultValue()
    {
        return defaultValue;
    }
    public void setDefaultValue(Object defaultValue)
    {
        this.defaultValue = defaultValue;
    }
    public int getColumnWidth()
    {
        return columnWidth;
    }
    public void setColumnWidth(int columnWidth)
    {
        this.columnWidth = columnWidth;
    }
    public boolean isAutoLine()
    {
        return autoLine;
    }
    public void setAutoLine(boolean autoLine)
    {
        this.autoLine = autoLine;
    }
}
