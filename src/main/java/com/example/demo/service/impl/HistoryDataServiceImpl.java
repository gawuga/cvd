package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entiy.HistoryQueryCondition;
import com.example.demo.entiy.data.*;
import com.example.demo.mapper.*;
import com.example.demo.service.DeviceService;
import com.example.demo.service.HistoryDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HistoryDataServiceImpl implements HistoryDataService {

    //@Resource
    //private DeviceMacMapper deviceMacMapper;

    @Resource
    private DeviceService deviceService;

    @Resource
    private LedMapper ledMapper;

    @Resource
    private LogoMapper logoMapper;

    @Resource
    private LabelMapper labelMapper;

    @Resource
    private CodeMapper codeMapper;
    @Resource
    private AoiMapper aoiMapper;


    @Resource
    private SocketMapper socketMapper;

    @Resource
    private TableMapper2 tableMapper2;

    @Override
    public List<Label> getLabelData(HistoryQueryCondition historyQueryCondition) {
        String tableName = historyQueryCondition.getDeviceType()+"_"+historyQueryCondition.getUserId();
        List<Label> labels = labelMapper.get(tableName
                ,historyQueryCondition.getTime()
                ,historyQueryCondition.getCurrentPage()
                ,historyQueryCondition.getPageSize(),historyQueryCondition.getDeviceLine(),historyQueryCondition.getDeviceName());
        for (Label l:labels){
            l.setExtraData(labelMapper.getExtraData(historyQueryCondition.getDeviceType()+"_extra_"+historyQueryCondition.getUserId(),Integer.parseInt(l.getId()+"")));
        }
        return labels;
    }

    @Override
    public List<Code> getCodeData(HistoryQueryCondition historyQueryCondition) {
        String tableName = historyQueryCondition.getDeviceType()+"_"+historyQueryCondition.getUserId();
        List<Code> codes = codeMapper.get(tableName
                ,historyQueryCondition.getTime()
                ,historyQueryCondition.getCurrentPage(),
                historyQueryCondition.getPageSize(),historyQueryCondition.getDeviceLine(),historyQueryCondition.getDeviceName());
        for (Code l:codes){
            l.setExtraData(codeMapper.getExtraData(historyQueryCondition.getDeviceType()+"_extra_"+historyQueryCondition.getUserId(),Integer.parseInt(l.getId()+"")));
        }
        return codes;
    }

    @Override
    public List<Led> getLedData(HistoryQueryCondition historyQueryCondition) {
        String tableName = historyQueryCondition.getDeviceType()+"_"+historyQueryCondition.getUserId();
        List<Led> leds =  ledMapper.get(tableName
                ,historyQueryCondition.getTime()
                ,historyQueryCondition.getCurrentPage()
                ,historyQueryCondition.getPageSize(),historyQueryCondition.getDeviceLine(),historyQueryCondition.getDeviceName());
        for (Led l:leds){
            l.setExtraData(ledMapper.getExtraData(historyQueryCondition.getDeviceType()+"_extra_"+historyQueryCondition.getUserId(),Integer.parseInt(l.getId()+"")));
        }
        return leds;
    }

    @Override
    public List<Logo> getLogoData(HistoryQueryCondition historyQueryCondition) {
        String tableName = historyQueryCondition.getDeviceType()+"_"+historyQueryCondition.getUserId();
        List<Logo> logo =  logoMapper.get(tableName
                ,historyQueryCondition.getTime()
                ,historyQueryCondition.getCurrentPage()
                ,historyQueryCondition.getPageSize(),historyQueryCondition.getDeviceLine(),historyQueryCondition.getDeviceName());
        for (Logo l:logo){
            l.setExtraData(labelMapper.getExtraData(historyQueryCondition.getDeviceType()+"_extra_"+historyQueryCondition.getUserId(),Integer.parseInt(l.getId()+"")));
        }
        return logo;
    }

    @Override
    public List<Socket> getSocketData(HistoryQueryCondition historyQueryCondition) {
        String tableName = historyQueryCondition.getDeviceType()+"_"+historyQueryCondition.getUserId();
        List<Socket> sockets = socketMapper.get(tableName
                ,historyQueryCondition.getTime()
                ,historyQueryCondition.getCurrentPage()
                ,historyQueryCondition.getPageSize(),historyQueryCondition.getDeviceLine(),historyQueryCondition.getDeviceName());
        for (Socket l:sockets){
            l.setExtraData(socketMapper.getExtraData(historyQueryCondition.getDeviceType()+"_extra_"+historyQueryCondition.getUserId(),Integer.parseInt(l.getId()+"")));
        }
        return sockets;
    }

    @Override
    public List<Aoi> getAoiData(HistoryQueryCondition historyQueryCondition) {
        String tableName = historyQueryCondition.getDeviceType()+"_"+historyQueryCondition.getUserId();
        List<Aoi> aois =  aoiMapper.get(historyQueryCondition.getDeviceType()+"_"+historyQueryCondition.getUserId()
                ,historyQueryCondition.getTime()
                ,historyQueryCondition.getCurrentPage()
                ,historyQueryCondition.getPageSize(),historyQueryCondition.getDeviceLine(),historyQueryCondition.getDeviceName());
        for (Aoi l:aois){
            l.setExtraData(socketMapper.getExtraData(historyQueryCondition.getDeviceType()+"_extra_"+historyQueryCondition.getUserId(),Integer.parseInt(l.getId()+"")));
        }
        return aois;
    }

    @Override
    public int insertLabelData(String userId, String productModel, String sn, String orderNo, String testTime,String deviceLine
            , String deviceName) {

        return labelMapper.insert("label_"+userId,productModel,sn,orderNo,testTime,deviceLine
                ,deviceName);
    }

    @Override
    public int insertCodeData(String userId, String deviceNo, String sn, String testTime,String deviceLine
            , String deviceName) {
        return codeMapper.insert("code_"+userId,deviceNo, sn,testTime,deviceLine
                ,deviceName);
    }

    @Override
    public int insertLedData(String userId, String productModel, String useTime, String result, String modelFail, String testTime,String deviceLine
            , String deviceName) {
        return ledMapper.insert("led_"+userId,productModel,useTime,result,modelFail,testTime,deviceLine
                ,deviceName);
    }

    @Override
    public int insertLogoData(String userId, String productId, String ngCode, String testResult, String useTime, String testTime,String deviceLine
            , String deviceName) {
        return logoMapper.insert("logo_"+userId, productId,ngCode,testResult,useTime,testTime,deviceLine
                ,deviceName);
    }

    @Override
    public int insertSocketData(String userId, String productModel, String ngCode, String testResult, String testTime, String sn,String deviceLine
            , String deviceName) {
        return socketMapper.insert("socket_"+userId,productModel,ngCode,testResult,testTime, sn,deviceLine
                ,deviceName);
    }

    @Override
    public int insertAoiData(String userId, String productModel, String ngCode, String testResult, String testTime, String sn,String deviceLine
            , String deviceName) {
        return aoiMapper.insert("aoi_"+userId,productModel,ngCode,testResult,testTime, sn,deviceLine
                ,deviceName);
    }

    /**
     * 插入额外字段的其中的一个值
     * @param tableName
     * @param fieldName
     * @param value
     * @param maxId
     * @return
     */
    public int insertExtraDataOneValue(String tableName ,String fieldName,String value,int maxId){

        ledMapper.insertExtra(tableName,maxId,fieldName,value);

        return 0;
    }

    @Override
    public List<Label> getLabelDataByMonth(HistoryQueryCondition historyQueryCondition) {
        String tableName = historyQueryCondition.getDeviceType()+"_"+historyQueryCondition.getUserId();
        List<Label> labels = labelMapper.getByMonth(tableName
                ,historyQueryCondition.getCurrentPage()
                ,historyQueryCondition.getPageSize(),historyQueryCondition.getDeviceLine()
                ,historyQueryCondition.getDeviceName(),historyQueryCondition.getYear(),historyQueryCondition.getMonth());
        for (Label l:labels){
            l.setExtraData(labelMapper.getExtraData(historyQueryCondition.getDeviceType()+"_extra_"+historyQueryCondition.getUserId(),Integer.parseInt(l.getId()+"")));
        }
        return labels;
    }

    @Override
    public List<Code> getCodeDataByMonth(HistoryQueryCondition historyQueryCondition) {
        String tableName = historyQueryCondition.getDeviceType()+"_"+historyQueryCondition.getUserId();
        List<Code> codes = codeMapper.getByMonth(tableName
                ,historyQueryCondition.getCurrentPage(),
                historyQueryCondition.getPageSize(),historyQueryCondition.getDeviceLine()
                ,historyQueryCondition.getDeviceName(),historyQueryCondition.getYear(),historyQueryCondition.getMonth());
        for (Code l:codes){
            l.setExtraData(codeMapper.getExtraData(historyQueryCondition.getDeviceType()+"_extra_"+historyQueryCondition.getUserId(),Integer.parseInt(l.getId()+"")));
        }
        return codes;
    }

    @Override
    public List<Led> getLedDataByMonth(HistoryQueryCondition historyQueryCondition) {
        String tableName = historyQueryCondition.getDeviceType()+"_"+historyQueryCondition.getUserId();
        List<Led> leds =  ledMapper.getByMonth(tableName

                ,historyQueryCondition.getCurrentPage()
                ,historyQueryCondition.getPageSize(),historyQueryCondition.getDeviceLine()
                ,historyQueryCondition.getDeviceName(),historyQueryCondition.getYear(),historyQueryCondition.getMonth());
        for (Led l:leds){
            l.setExtraData(ledMapper.getExtraData(historyQueryCondition.getDeviceType()+"_extra_"+historyQueryCondition.getUserId(),Integer.parseInt(l.getId()+"")));
        }
        return leds;
    }

    @Override
    public List<Logo> getLogoDataByMonth(HistoryQueryCondition historyQueryCondition) {
        String tableName = historyQueryCondition.getDeviceType()+"_"+historyQueryCondition.getUserId();
        List<Logo> logo =  logoMapper.getByMonth(tableName
                ,historyQueryCondition.getCurrentPage()
                ,historyQueryCondition.getPageSize(),historyQueryCondition.getDeviceLine()
                ,historyQueryCondition.getDeviceName(),historyQueryCondition.getYear(),historyQueryCondition.getMonth());
        for (Logo l:logo){
            l.setExtraData(labelMapper.getExtraData(historyQueryCondition.getDeviceType()+"_extra_"+historyQueryCondition.getUserId(),Integer.parseInt(l.getId()+"")));
        }
        return logo;
    }

    @Override
    public List<Socket> getSocketDataByMonth(HistoryQueryCondition historyQueryCondition) {
        String tableName = historyQueryCondition.getDeviceType()+"_"+historyQueryCondition.getUserId();
        List<Socket> sockets = socketMapper.getByMonth(tableName
                ,historyQueryCondition.getCurrentPage()
                ,historyQueryCondition.getPageSize()
                ,historyQueryCondition.getDeviceLine(),historyQueryCondition.getDeviceName()
                ,historyQueryCondition.getYear(),historyQueryCondition.getMonth());
        for (Socket l:sockets){
            l.setExtraData(socketMapper.getExtraData(historyQueryCondition.getDeviceType()+"_extra_"+historyQueryCondition.getUserId(),Integer.parseInt(l.getId()+"")));
        }
        return sockets;
    }

    @Override
    public List<Aoi> getAoiDataByMonth(HistoryQueryCondition historyQueryCondition) {
        String tableName = historyQueryCondition.getDeviceType()+"_"+historyQueryCondition.getUserId();
        List<Aoi> aois =  aoiMapper.getByMonth(historyQueryCondition.getDeviceType()+"_"+historyQueryCondition.getUserId()
                ,historyQueryCondition.getCurrentPage()
                ,historyQueryCondition.getPageSize(),
                historyQueryCondition.getDeviceLine(),
                historyQueryCondition.getDeviceName(),historyQueryCondition.getYear(),historyQueryCondition.getMonth());
        for (Aoi l:aois){
            l.setExtraData(socketMapper.getExtraData(historyQueryCondition.getDeviceType()+"_extra_"+historyQueryCondition.getUserId(),Integer.parseInt(l.getId()+"")));
        }
        return aois;
    }


    public void insertExtraData(JSONObject data,String currentUser,String deviceType){

       // String tableName = deviceType+"_extra_"+currentUser;
        int maxId = tableMapper2.lastId(deviceType+"_"+currentUser);
        String tableName = deviceType+"_extra_"+currentUser;
        for (String fieldName: deviceService.getDeviceExtraFields(deviceType,Integer.parseInt(currentUser))){
            try {
                insertExtraDataOneValue(tableName,fieldName,data.getString(fieldName),maxId);
            }catch (Exception e){
                System.out.println(fieldName+"----没有值啊");
                continue;
            }
        }

        System.out.println("getAoiData");
    }


}
