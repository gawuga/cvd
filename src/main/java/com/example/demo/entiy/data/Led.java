package com.example.demo.entiy.data;

import lombok.Data;

/**
 * led支架检测数据类
 *
 * ProductModel VARCHAR(64),   ;测试型号
 * TestTime DATETIME, ;检测时间
 * result VARCHAR(10), ;测试结果
 * usetime VARCHAR(64), ;检测用时
 * modelfail VARCHAR(64) ;匹配是否ng
 */
@Data
public class Led extends BasicData{
    //主键
    private Long id;
    private String productModel;
    private String testTime;
    private String result;
    private String useTime;
    private String modelFail;

}
