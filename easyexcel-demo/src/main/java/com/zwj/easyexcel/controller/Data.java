package com.zwj.easyexcel.controller;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

@lombok.Data
public class Data {
    @ExcelProperty("JCode" )
    private String JCode;

    @ExcelProperty("stockCode")
    @ColumnWidth(30)
    private String stockCode;
}
