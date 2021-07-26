package com.zwj.easyexcel.controller;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

@lombok.Data
public class StatisticsData {
    @ExcelProperty("JCode" )
    private String JCode;

    @ExcelProperty("perDAy")
    @ColumnWidth(30)
    private String perDAy;
    
    @ExcelProperty("reMain")
    @ColumnWidth(30)
    private String reMain;
    
    @ExcelProperty("account")
    @ColumnWidth(30)
    private String account;
    
    
    @ExcelProperty("totalIncomePercent")
    @ColumnWidth(30)
    private double totalIncomePercent;
    
    @ExcelProperty("take1")
    @ColumnWidth(30)
    private String take1;
    
    @ExcelProperty("take2")
    @ColumnWidth(30)
    private String take2;
    
    @ExcelProperty("take3")
    @ColumnWidth(30)
    private String take3;
}
