package com.example.demo.entiy;

import lombok.Data;


/**
 * 设备类型类
 */
@Data
public class Device {
    private Integer userId;
    private String date;
    //检测设备类型
    private String deviceType;
    //设备id
    private String deviceId;
    //检测数据的额外字段
    private String deviceExtraField;

    //检测数据的原有字段
    private String deviceCommonField;
}
