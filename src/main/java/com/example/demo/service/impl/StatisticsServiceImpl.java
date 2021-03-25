package com.example.demo.service.impl;

import com.example.demo.entiy.BarData;
import com.example.demo.entiy.PieData;
import com.example.demo.entiy.PieResult;
import com.example.demo.entiy.Result;
import com.example.demo.mapper.*;
import com.example.demo.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 统计服务类
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private DataMapper dataMapper;


    @Override
    public BarData getBarData(String deviceType, int userId,String year, String month) {
        List<Result> list = dataMapper.countDayChange(deviceType+"_"+userId,year,month);

        return barData(list);
    }

    @Override
    public PieData getPieData(String deviceType, int userId, String year, String month) {
        List<PieResult> list = dataMapper.countNgCode(deviceType+"_"+userId,year,month);

        return pieData(list);
    }

    @Override
    public BarData getBarDataByMac(String deviceType, int userId, String year, String month, String deviceLine, String deviceName) {
        List<Result> list = dataMapper.countDayChangeByMac(deviceType+"_"+userId,year,month,deviceLine,deviceName);
        return barData(list);
    }

    @Override
    public PieData getPieDataByMac(String deviceType, int userId, String year, String month, String deviceLine, String deviceName) {
        List<PieResult> list = dataMapper.countNgCodeByMac(deviceType+"_"+userId,year,month,deviceLine,deviceName);
        return pieData(list);
    }

    public PieData pieData(List<PieResult> list){
        List<Integer> value = new ArrayList<>();
        List<String> name = new ArrayList<>();
        for (PieResult r:list){
            value.add(r.getValue());
            name.add(r.getNgCode()+"");
        }
        PieData pieData = new PieData();
        pieData.setValues(value);
        pieData.setNames(name);
        return pieData;
    }

    public BarData barData(List<Result> list ){
        List<String> xData = new ArrayList<>();
        List<String> yData = new ArrayList<>();
        for (Result r:list){
            yData.add(r.getCo()+"");
            xData.add(r.getTDate()+"");
        }
        BarData barData = new BarData();
        barData.setYData(yData);
        barData.setXData(xData);
        return barData;
    }

}
