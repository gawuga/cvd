package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entiy.Device;
import com.example.demo.entiy.DeviceFields;
import com.example.demo.entiy.DeviceMac;
import com.example.demo.entiy.UserDisplayCard;
import com.example.demo.mapper.DeviceFieldMapper;
import com.example.demo.mapper.DeviceMacMapper;
import com.example.demo.mapper.DeviceMapper;
import com.example.demo.mapper.UserDisplayCardMapper;
import com.example.demo.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 设备模块服务类
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    @Resource
    private UserDisplayCardMapper cardMapper;

    @Resource
    private DeviceMapper deviceMapper;

    @Resource
    private DeviceMacMapper deviceMacMapper;

    @Autowired
    private DeviceFieldMapper fieldMapper;

    /**
     * 返回用户所有的设备
     * @param userId
     * @return
     */
    @Override
    public List<Device> getDeviceByUserId(int userId) {
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);


        return deviceMapper.selectList(queryWrapper);
    }

    /**
     * 删除设备
     * @param device
     * @return
     */
    @Override
    public int deleteDevice(Device device) {
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", device.getUserId());
        queryWrapper.eq("device_type", device.getDeviceType());
        deviceMapper.delete(queryWrapper);
        return 0;
    }

    /**
     * 添加设备
     * @param device
     * @return
     */
    @Override
    public int addDevice(Device device) {
        String fields = "";
        int i = 0;
        for (String s:this.getDeviceFields(device.getDeviceType())){
            if (i!=0)fields+="," ;
            fields+=s;
            i++;
        }
        device.setDeviceCommonField(fields);
        System.out.println(fields);
        return deviceMapper.insert(device);
    }

    /**
     * 修改检测数据的额外字段
     * @param device
     * @return
     */
    @Override
    public int updateDeviceExtraFields(Device device) {

        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", device.getUserId());
        queryWrapper.eq("device_type", device.getDeviceType());
        //Device d = deviceMapper.selectOne(queryWrapper);
       // d.setDeviceExtraField(device.getDeviceExtraField());
       // int i =
        return deviceMapper.update(device,queryWrapper);
    }

    @Override
    public int updateDeviceMac(DeviceMac deviceMac) {
        QueryWrapper<DeviceMac> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", deviceMac.getUserId());
        queryWrapper.eq("device_type", deviceMac.getDeviceType());
        queryWrapper.eq("device_mac", deviceMac.getDeviceMac());

        return deviceMacMapper.update(deviceMac,queryWrapper);
    }

    @Override
    public int deleteAllDeviceMac(List<DeviceMac> deviceMacs) {
        for (DeviceMac d:deviceMacs){
            deviceMacMapper.deleteById(d.getId());
        }
        return 0;
    }

    /**
     * 得到该用户下该生产线下的所有设备
     * @param deviceType
     * @param userId
     * @param deviceLine
     * @return
     */
    @Override
    public List<DeviceMac> getDeviceNameByDeviceLine(String deviceType, int userId, String deviceLine) {
        QueryWrapper<DeviceMac> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("device_type", deviceType);
        queryWrapper.eq("device_line", deviceLine);
        return deviceMacMapper.selectList(queryWrapper);
    }

    @Override
    public List<DeviceMac> getDeviceLineByUserId(int userId) {
        System.out.println(("----- selectAll method test ------"));
        QueryWrapper<DeviceMac> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("device_line");
        queryWrapper.eq("user_id", userId);
        queryWrapper.groupBy("device_line");
        List<DeviceMac> devicesList = deviceMacMapper.selectList(queryWrapper);
        return devicesList;
    }

    @Override
    public List<DeviceMac> getDeviceTypeByUserId(int userId, String deviceLine) {
        System.out.println(("----- selectAll method test ------"));
        QueryWrapper<DeviceMac> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("device_type");
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("device_line", deviceLine);
        queryWrapper.groupBy("device_type");
        List<DeviceMac> devicesList = deviceMacMapper.selectList(queryWrapper);
        return devicesList;
    }

    @Override
    public int addUserDisplayDevice(UserDisplayCard card) {
        return cardMapper.insert(card);
    }

    @Override
    public List<UserDisplayCard> getCardByUserId(int userId) {
        QueryWrapper<UserDisplayCard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return cardMapper.selectList(queryWrapper);
    }

    @Override
    public int delUserDisplayDevice(UserDisplayCard card) {
        QueryWrapper<UserDisplayCard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_mac", card.getDeviceMac());
        queryWrapper.eq("user_id", card.getUserId());
        return cardMapper.delete(queryWrapper);
    }




    @Override
    public Set<String> getDeviceTypeList(int userId) {


        List<Device> devicesList = getDeviceByUserId(userId);

        Set<String> deviceSet = new HashSet<>();
        for(Device device:devicesList) {
           // String s = device.getDeviceType() + "," + DeviceType.type.get(device);
           // System.out.println(s);
            deviceSet.add(device.getDeviceType());

        }
        return deviceSet;
    }

    @Override
    public List<Device> getDeviceByPage(int userId,int currentPage,int pageSize) {
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Page<Device> page = new Page<>(currentPage, pageSize);
        IPage<Device> templateIPage = deviceMapper.selectPage(page, queryWrapper);
        return templateIPage.getRecords();
    }


    /**
     * 返回六种设备的检测数据字段
     * @param deviceType
     * @return
     */
    @Override
    public List<String> getDeviceFields(String deviceType) {
        System.out.println(("----- fields method test ------"));
        QueryWrapper<DeviceFields> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_type", deviceType);

        List<String> devicesList = new ArrayList<>();

        for(DeviceFields device:fieldMapper.selectList(queryWrapper)) {
            devicesList.add(device.getField());
        }

        return devicesList;
    }

    /***
     * 返回用户的自己添加的额外数据字段
     * @param deviceType
     * @param userId
     * @return
     */
    @Override
    public List<String> getDeviceExtraFields(String deviceType,int userId) {
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("device_type", deviceType);
        Device d =  deviceMapper.selectOne(queryWrapper);
        return Arrays.asList(d.getDeviceExtraField().split(","));
    }


    /**
     * 返回用户的检测设备对应的检测数据的所有字段
     * @return
     */
    @Override
    public List<String> getDeviceAllField(String deviceType,int userId) {
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("device_type", deviceType);
        queryWrapper.select("device_extra_field","device_common_field");
        List<String> rs = new ArrayList<>();
        Device d =  deviceMapper.selectOne(queryWrapper);


        if (d.getDeviceExtraField()!=null){
            String[] a = d.getDeviceExtraField().split(",");
            for (String s:a){
                rs.add(s);
            }
        }
        if (d.getDeviceCommonField()!=null){
            String[] b = d.getDeviceCommonField().split(",");

            for (String s:b){
                rs.add(s.trim());
            }
        }

        return rs;
    }

    @Override
    public List<DeviceMac> getDeviceMacByPage(int userId, int currentPage, int pageSize) {
        QueryWrapper<DeviceMac> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Page<DeviceMac> page = new Page<>(currentPage, pageSize);
        IPage<DeviceMac> templateIPage = deviceMacMapper.selectPage(page,queryWrapper);
        return templateIPage.getRecords();

    }

    @Override
    public int addDeviceMac(DeviceMac deviceMac) {
        deviceMac.setDeviceMac(deviceMac.getDeviceMac().trim());
        QueryWrapper<DeviceMac> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_mac", deviceMac.getDeviceMac());
        queryWrapper.eq("user_id", deviceMac.getUserId());

        if (deviceMacMapper.selectOne(queryWrapper)==null)    {
            Date time = new Date(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String current = sdf.format(time);
            deviceMac.setDate(current);
            deviceMacMapper.insert(deviceMac);
            return 1;
        }
        return 0;

    }

    @Override
    public int deleteDeviceMac(DeviceMac deviceMac) {
        QueryWrapper<DeviceMac> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("device_mac", deviceMac.getDeviceMac());
        queryWrapper.eq("user_id", deviceMac.getUserId());
        deviceMacMapper.delete(queryWrapper);
        return 0;
    }

    @Override
    public List<DeviceMac> getDeviceMacByPageAndDeviceType(String deviceType, int userId, int currentPage, int pageSize) {
        QueryWrapper<DeviceMac> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("device_type", deviceType);
        Page<DeviceMac> page = new Page<>(currentPage, pageSize);
        IPage<DeviceMac> templateIPage = deviceMacMapper.selectPage(page,queryWrapper);
        return templateIPage.getRecords();

    }

    @Override
    public List<DeviceMac> getDeviceMacByDeviceType(String deviceType, int userId) {
        QueryWrapper<DeviceMac> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("device_type", deviceType);

        return deviceMacMapper.selectList(queryWrapper);
    }


}
