package com.example.demo.service.impl;

import com.example.demo.mapper.TableMapper2;
import com.example.demo.service.TableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 动态表服务类
 */
@Service
public class TableServiceImpl implements TableService {
    @Resource
    private TableMapper2 tableMapper2;
    @Override
    public void createTable(String deviceType, int userId) {
        String tableName = deviceType+"_"+userId;
        if (deviceType.equals("led")){
            tableMapper2.createLedTable(tableName);

            System.out.println("创建led设备表成功");
        }else if (deviceType.equals("logo")){
            tableMapper2.createLogoTable(tableName);
            System.out.println("创建logo设备表成功");
        }else if (deviceType.equals("code")){
            tableMapper2.createCodeTable(tableName);
            System.out.println("创建code设备表成功");
        }else if (deviceType.equals("label")){
            tableMapper2.createLabelTable(tableName);
            System.out.println("创建label设备表成功");
        }else {
            tableMapper2.createAoiOrSocketTable(tableName);
            System.out.println("创建aoi/socket设备表成功");
        }
        //创建额外的数据表
        tableMapper2.createDataExtraTable(deviceType+"_extra_"+userId);
    }

    @Override
    public boolean exitsTable(String deviceType, int userId) {
        return false;
    }

    @Override
    public void dropTable(String deviceType, int userId) {
        tableMapper2.dropExistTable(deviceType+"_"+userId);
        //删除额外的数据
        tableMapper2.dropExistTable(deviceType+"_extra_"+userId);
    }

    @Override
    public int createLedTable(String tableName) {
        tableMapper2.createLedTable(tableName);
        return 0;
    }

    @Override
    public int createLogoTable(String tableName) {
        tableMapper2.createLogoTable(tableName);
        return 0;
    }

    @Override
    public int createCodeTable(String tableName) {
        tableMapper2.createCodeTable(tableName);
        return 0;
    }

    @Override
    public int createLabelTable(String tableName) {
        tableMapper2.createLabelTable(tableName);
        return 0;
    }

    @Override
    public int createAoiOrSocketTable(String tableName) {
        tableMapper2.createAoiOrSocketTable(tableName);
        return 0;
    }
}
