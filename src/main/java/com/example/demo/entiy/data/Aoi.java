package com.example.demo.entiy.data;

import lombok.Data;

/**
 * AOI检测数据类
 *  Item=TestTime varchar(128), SN varchar(64),
 *  * varchar(64), ProductModel varchar(64), TestResult varchar(10), NGCode varchar(64)
 */
@Data
public class Aoi extends BasicData{
    //主键
    private Long id;
    private String productModel;
    private String testTime;
    private String testResult;
    private String ngCode;
    private String sn;

}

