package com.example.demo.entiy;

import lombok.Data;


/**
 * 设备数据字段类
 */

@Data
public class DeviceFields {
    //设备类型
    private String deviceType;
    //设备字段
    private String field;
    //注释---字段的中文翻译
    private String annotation;
}
