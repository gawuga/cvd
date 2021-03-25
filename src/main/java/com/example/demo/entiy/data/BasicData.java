package com.example.demo.entiy.data;

import com.example.demo.entiy.ExtraData;
import lombok.Data;

import java.util.List;

/**
 * 检测数据应该有的基础信息
 */
@Data
public class BasicData {
    //展示额外的数据
    private List<ExtraData> extraData;

    //展示数据的时候 应该有该数据时来自哪条生产线
    private String deviceLine;
    //设备的名称
    private String deviceName;
}
