package com.example.demo.service;

import com.example.demo.entiy.BarData;
import com.example.demo.entiy.PieData;

public interface StatisticsService {

    /**
     * 按照设备类型进行统计
     * @param deviceType
     * @param userId
     * @param year
     * @param month
     * @return
     */
    public BarData getBarData(String deviceType, int userId, String year, String month);
public PieData getPieData(String deviceType, int userId, String year, String month);

    /**
     * 按照设备统计
     * @param deviceType
     * @param userId
     * @param year
     * @param month
     * @return
     */
    public BarData getBarDataByMac(String deviceType, int userId, String year, String month, String deviceLine, String deviceName);
    public PieData getPieDataByMac(String deviceType, int userId, String year, String month, String deviceLine, String deviceName);
}
