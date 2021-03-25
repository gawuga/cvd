package com.example.demo.entiy;

import lombok.Data;

@Data
public class BasicDevice {
    //主键
    private Long id;
    private Integer userId;
    private String deviceType;
    private String deviceMac;
    //设备别名
    private String deviceName;
    //生产线号
    private String deviceLine;
}
