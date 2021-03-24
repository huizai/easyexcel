package com.zwj.entity;
import lombok.Data;

@Data
public class Fund {
//    private Integer id;
    private String fundcode;
    private String timedate;
    private double preday;
    private double currentday;
    private double wave;
    private String fundname;
}