package com.example.demo.controller;

import com.example.demo.entiy.*;
import com.example.demo.service.DeviceService;
import com.example.demo.service.TableService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.*;

/**
 * 设备模块控制类
 */
@RestController
@RequestMapping("device")
public class DeviceController {
    @Resource
    private DeviceService deviceService;
    @Resource
    private TableService tableService;
    private Map<String,String> deviceType;

    public DeviceController() {
        this.deviceType = new HashMap<>();
        deviceType.put("led","led");
        deviceType.put("label","标签");
        deviceType.put("logo","logo");
        deviceType.put("code","条形码");
        deviceType.put("aoi","aoi");
        deviceType.put("socket","插座");
    }

    /**
     * 得到用户拥有的的设备类型的名字
     * @return
     */
    @GetMapping("/deviceTypeList")
    public List<String > getDeviceTypeList(@PathParam("userId") int userId){
        List<String> ans = new ArrayList<>();
        for (String s:deviceService.getDeviceTypeList(1000)){
            ans.add(s+","+deviceType.get(s));
        }
        return ans;
    }


    /**
     * 返回用户所有的设备类型
     * @param userId
     * @return
     */
    @GetMapping("/deviceType")
    public List<Device> getDeviceByUserId(@PathParam("userId") int userId) {
        List<Device> devices = deviceService.getDeviceByUserId(userId);
        for (Device d:devices){
            String s = d.getDeviceExtraField().replace(","," ");
            /**
             *   for (String field:getDeviceFields(d.getDeviceType())){
             *                 s+=" " + field;
             *             }
             */
            System.out.println(s);
            d.setDeviceExtraField(s);
        }

        return devices;
    }

    /**
     * 删除设备
     *
     * @return
     */
    @GetMapping("/deleteDeviceType")
    public String deleteDeviceType(@PathParam("userId") int userId, @PathParam("deviceType") String deviceType) {

        Device device = new Device();
        device.setDeviceType(deviceType);
        device.setUserId(userId);
        int i = deviceService.deleteDevice(device);
        System.out.println(i);
        /**
         * 该用户设备表也要删除
         */
        tableService.dropTable(deviceType,userId);

        //既然表都删除了 那么该类型下的所有设备都得删除
        deviceService.deleteAllDeviceMac(deviceService.getDeviceMacByDeviceType(deviceType,userId));
        return "success";
    }
    /**
     * 删除设备
     *
     * @return
     */
    @PostMapping("/updateExtraFields")
    public String updateExtraFields(@RequestBody Device device) {
        System.out.println("updateExtraFields");
        System.out.println(device.getDeviceExtraField());
       deviceService.updateDeviceExtraFields(device);
        return "success";
    }

    /**
     * 添加设备
     * @param device
     * @return
     */
    @PostMapping("/addDeviceType")
    public String addDeviceType(@RequestBody Device device) {
        int i = deviceService.addDevice(device);
        System.out.println(i);
        /**
         * 该用户设备表也要新建
         */
        String type = device.getDeviceType();
        tableService.createTable(type,device.getUserId());


        return "success";
    }

    /**
     * 返回用户所有的设备----分页查询
     * @param userId
     * @return
     */
    @GetMapping("/deviceByPage")
    public List<Device> getDeviceByPage(@PathParam("userId") int userId,@PathParam("currentPage") int currentPage,@PathParam("pageSize") int pageSize) {


        return deviceService.getDeviceByPage(userId,currentPage,pageSize);
    }

    /**
     * 返回六种设备的检测数据字段
     * @param deviceType
     * @return
     */
    @GetMapping("/deviceFields")
    public List<String> getDeviceFields(@PathParam("deviceType") String deviceType) {

     return deviceService.getDeviceFields(deviceType);
    }

    /**
     * 返回六种设备的检测数据字段
     * @param deviceType
     * @return
     */
    @GetMapping("/deviceAllFields")
    public List<String> getDeviceAllFields(@PathParam("userId") int userId,@PathParam("deviceType") String deviceType) {

        return deviceService.getDeviceAllField(deviceType,userId);
    }

    @GetMapping("/getDeviceMac")
    public List<DeviceMac> getDeviceMacByPage(HistoryQueryCondition queryCondition) {
        System.out.println(".....getDeviceMac");
        String type = queryCondition.getDeviceType();
        int userId = queryCondition.getUserId();
        int page =  queryCondition.getCurrentPage();
        int pageSize = queryCondition.getPageSize();
    if (queryCondition.getDeviceType()=="") return deviceService.getDeviceMacByPage(userId,
            page ,pageSize);
    return deviceService.getDeviceMacByPageAndDeviceType(type,userId,
            page ,pageSize);

    }

    /**
     * 添加该设备类型下的设备
     * @param deviceMac
     * @return
     */
    @PostMapping("/addDeviceMac")
    public String addDeviceMac(@RequestBody DeviceMac deviceMac) {
        System.out.println(".....addDeviceMac");
        int i = deviceService.addDeviceMac(deviceMac);
        return i==0?"error":"success";
    }

    /**
     * 删除该类型下的设备
     * @param deviceMac
     * @return
     */
    @GetMapping("/deleteDeviceMac")
    public String deleteDeviceMac(DeviceMac deviceMac) {
        System.out.println(".....deleteDeviceMac");
        deviceService.deleteDeviceMac(deviceMac);
        //前端展示的设备都删除
        UserDisplayCard u = new UserDisplayCard();
           u.setDeviceLine(deviceMac.getDeviceLine());
           u.setDeviceMac(deviceMac.getDeviceMac());
           u.setUserId(deviceMac.getUserId());
        System.out.println("前端展示的设备都删除");
       deviceService.delUserDisplayDevice(u);
        //该设备的数据都删除
        return"success";
    }

    @PostMapping("/updateDeviceMac")
    public String updateDeviceMac(@RequestBody DeviceMac deviceMac) {

        System.out.println(".....updateDeviceMac");
        System.out.println(deviceMac.getDeviceMac());
        deviceService.updateDeviceMac(deviceMac);
        return"success";
    }



    /**
     * 返回该类型下的设备---
     * @param userId

     * @return
     */
    @GetMapping("/getDeviceLine")
    public Set<String> getDeviceMacByDeviceType(@PathParam("deviceType")String deviceType, @PathParam("userId")int userId){
        Set<String> lines = new HashSet<>();
        for (DeviceMac d:deviceService.getDeviceMacByDeviceType(deviceType,userId)){
            lines.add(d.getDeviceLine());
        }
        return lines;
    }

    /**
     * 得到该用户下该生产线下的所有设备
     * @return
     */
    @GetMapping("/getDeviceName")
    public List<DeviceMac> getDeviceNameByDeviceLine(@PathParam("deviceType")String deviceType, @PathParam("userId")int userId,@PathParam("deviceLine")String deviceLine){
        return deviceService.getDeviceNameByDeviceLine(deviceType,userId,deviceLine);
    }


    /**
     * 得到该用户下所有该生产线下
     * @return
     */
    @GetMapping("/getLine")
    public List<DeviceMac> getDeviceLineByUserId(@PathParam("userId")int userId){
        return deviceService.getDeviceLineByUserId(userId);
    }

    /**
     * 得到该用户下所有该生产线下
     * @return
     */
    @GetMapping("/getDeviceTypeByLine")
    public List<DeviceMac> getDeviceTypeByUserId(@PathParam("userId")int userId,@PathParam("deviceLine")String deviceLine){
        return deviceService.getDeviceTypeByUserId(userId,deviceLine);
    }

    /**
     * 得到该用户下所有该生产线下
     * @return
     */
    @PostMapping("/addUserDisplayDevice")
    public String addUserDisplayDevice(@RequestBody UserDisplayCard card){
        return deviceService.addUserDisplayDevice(card)==0?"error":"success";
    }

    /**
     * 得到该用户下所有该生产线下
     * @return
     */
    @GetMapping("/delUserDisplayDevice")
    public String delUserDisplayDevice(UserDisplayCard card){
        return deviceService.delUserDisplayDevice(card)==0?"error":"success";
    }

    /**
     * 得到该用户下前端展示的设备
     * @return
     */
    @GetMapping("/getCardByUserId")
    public List<UserDisplayCard> getCardByUserId(@PathParam("userId")int userId){

        return deviceService.getCardByUserId(userId);
    }
}
