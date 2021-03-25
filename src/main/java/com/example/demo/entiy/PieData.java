package com.example.demo.entiy;

import lombok.Data;

import java.util.List;

@Data
public class PieData {

    //x轴坐标
    List<Integer> values;
    //y轴坐标
    List<String> names;
}
