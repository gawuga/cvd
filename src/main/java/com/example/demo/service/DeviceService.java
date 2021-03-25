package com.example.demo.service;

import com.example.demo.entiy.Device;
import com.example.demo.entiy.DeviceMac;
import com.example.demo.entiy.UserDisplayCard;

import java.util.List;
import java.util.Set;

public interface DeviceService {
    //通过用户Id得到拥有的设备数据
    public List<Device>  getDeviceByUserId(int userId);

    //删除设备
    public int deleteDevice(Device device);

    //添加设备
    public int addDevice(Device device);


    //得到用户拥有的设备类型
    public Set<String> getDeviceTypeList(int userId);

    //分页查询
    public List<Device> getDeviceByPage(int userId, int currentPage, int pageSize);


    /**
     * 得到六种设备的必须字段
     * @return
     */
    public List<String> getDeviceFields(String deviceType);

    public List<String> getDeviceExtraFields(String deviceType, int userId);

    //得到用户该检测设备所有字段
    public List<String> getDeviceAllField(String deviceType, int userId);

    /**
     * 返回所有设备
     * @param userId
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<DeviceMac> getDeviceMacByPage(int userId, int currentPage, int pageSize);

    /**
     * 添加设备
     * @param deviceMac
     * @return
     */
    public int addDeviceMac(DeviceMac deviceMac);

    /**
     * 删除设备
     * @param deviceMac
     * @return
     */
    public int deleteDeviceMac(DeviceMac deviceMac);

    /**
     * 返回该类型下的设备---分页
     * @param userId
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<DeviceMac> getDeviceMacByPageAndDeviceType(String deviceType, int userId, int currentPage, int pageSize);



    /**
     * 返回该类型下的设备---
     * @param userId

     * @return
     */
    public List<DeviceMac> getDeviceMacByDeviceType(String deviceType, int userId);

    /**
     * 更新该类型下检测数据的额外字段的值
     * @param device
     * @return
     */
    public int updateDeviceExtraFields(Device device);

    /**
     * 更新该类型下的设备
     * @param deviceMac
     * @return
     */
    public int  updateDeviceMac(DeviceMac deviceMac);

    /**
     * 删除该类型下的所有设备
     * @param deviceMacs
     * @return
     */
    public int deleteAllDeviceMac(List<DeviceMac> deviceMacs);

    /**
     * 得到该用户下该生产线下的所有设备
     * @return
     */
    public List<DeviceMac> getDeviceNameByDeviceLine(String deviceType, int userId, String deviceLine);

    /**
     * 得到该用户下所有生产线
     * @return
     */
    public List<DeviceMac> getDeviceLineByUserId(int userId);

    /**
     * 得到该用户下生产线所有设备类型
     * @return
     */
    public List<DeviceMac> getDeviceTypeByUserId(int userId, String deviceLine);

    /**
     * 添加用户在前端显示的卡片--设备
     * @param card
     * @return
     */
    public int addUserDisplayDevice(UserDisplayCard card);

    public List<UserDisplayCard> getCardByUserId(int userId);

    public int delUserDisplayDevice(UserDisplayCard card);




}
