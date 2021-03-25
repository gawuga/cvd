package com.example.demo.controller;

import com.example.demo.entiy.BarData;
import com.example.demo.entiy.BaseQueryCondition;
import com.example.demo.entiy.PieData;
import com.example.demo.service.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("statistics")
public class StatisticsController {

    @Resource
    private StatisticsService statisticsService;

    /**
     * 返回用户所有的设备----分页查询
     * @param
     * @return
     */
    @GetMapping("/getBarData")
    public BarData getBarData(BaseQueryCondition condition) {

        String year = condition.getTime().split("-")[0];
        String month = condition.getTime().split("-")[1];
        System.out.println(year + "-" + month);
        return statisticsService.getBarData(condition.getDeviceType(),condition.getUserId(),year,month);
    }


    @GetMapping("/getPieData")
    public PieData getPieData(BaseQueryCondition condition) {

        String year = condition.getTime().split("-")[0];
        String month = condition.getTime().split("-")[1];
        System.out.println(year + "-" + month);
        return statisticsService.getPieData(condition.getDeviceType(),condition.getUserId(),year,month);
    }



    @GetMapping("/getBarDataByDevice")
    public BarData getBarDataByMac(BaseQueryCondition condition) {

        String year = condition.getTime().split("-")[0];
        String month = condition.getTime().split("-")[1];
        System.out.println(year + "-" + month);
        return statisticsService.getBarDataByMac(condition.getDeviceType(),condition.getUserId()
                ,year,month,condition.getDeviceLine(),condition.getDeviceName());
    }


    @GetMapping("/getPieDataByDevice")
    public PieData getPieDataByMac(BaseQueryCondition condition) {

        String year = condition.getTime().split("-")[0];
        String month = condition.getTime().split("-")[1];
        System.out.println(year + "-" + month);
        return statisticsService.getPieDataByMac(condition.getDeviceType(),condition.getUserId()
                ,year,month,condition.getDeviceLine(),condition.getDeviceName());
    }
}
