package com.example.demo.entiy;

import lombok.Data;

import java.util.List;

/**
 * 柱状图数据
 */
@Data
public class BarData {
    //x轴坐标
    List<String> xData;
    //y轴坐标
    List<String> yData;
}
