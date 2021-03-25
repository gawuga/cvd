package com.example.demo.entiy.data;

import lombok.Data;

/**
 * 条形码检测数据类
 *
 * CustomName varchar(128), CustomName varchar(64),DeviceNo varchar(64),
 * TestTime varchar(128),CycleTime varchar(64), SN varchar(64),CodeMessage varchar(128)
 */
@Data
public class Code extends BasicData{
    //主键
    private Long id;
    private String deviceNo;
    private String testTime;
   // private String result;
    private String sn;

}
