package com.example.demo.service;

/***
 * 动态表服务类
 */
public interface TableService {

    //新建用户设备数据表
    public void createTable(String deviceType, int userId);

    //该用户是否存在用户设备表
    public boolean exitsTable(String deviceType, int userId);


    //删除用户设备表
    public void dropTable(String deviceType, int userId);


    int createLedTable(String tableName);


    int createLogoTable(String tableName);

    int createCodeTable(String tableName);

    int createLabelTable(String tableName);


    int createAoiOrSocketTable(String tableName);
}
