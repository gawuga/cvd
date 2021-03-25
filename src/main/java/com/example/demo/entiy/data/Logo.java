package com.example.demo.entiy.data;

import lombok.Data;

/**
 * logo检测数据类
 * Item=ReportNum INT PRIMARY KEY AUTO_INCREMENT,   ;id号
 * ProductModel VARCHAR(64), ;测试型号
 * TestTime VARCHAR(64),  ;检测时间
 * UseTime INT,     ;检测用时
 * TestResult VARCHAR(10),  ;检测结果
 * NGCode VARCHAR(64),   ;ngcode
 * Flag INT,    ;标记位
 * ProductID VARCHAR(64)  ;sn号
 */
@Data
public class Logo extends BasicData{
    //主键
    private Long id;
    private String productModel;
    private String testTime;
    private String testResult;
    private String useTime;
    private String ngCode;
    private String productId;

}
