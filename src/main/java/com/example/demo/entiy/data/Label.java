package com.example.demo.entiy.data;

import lombok.Data;

/**
 * 标签检测数据类
 * TestTime varchar(128), SN varchar(64), OrderNo varchar(64), ProductModel varchar(64)
 */
@Data
public class Label extends BasicData{
    //主键
    private Long id;
    private String productModel;
    private String testTime;
    private String orderNo;
    private String sn;

}
