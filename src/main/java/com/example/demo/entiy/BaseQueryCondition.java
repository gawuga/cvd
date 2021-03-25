package com.example.demo.entiy;

import lombok.Data;

@Data
public class BaseQueryCondition {
    //用户id
    private int userId;

    private String deviceLine;

    private String deviceName;
    //数据时间范围
    private String time;

    //设备类型
    private String deviceType;
}
