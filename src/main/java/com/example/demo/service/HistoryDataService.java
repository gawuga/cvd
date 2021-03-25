package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entiy.HistoryQueryCondition;
import com.example.demo.entiy.data.*;

import java.util.List;

public interface HistoryDataService {

    //得到标签数据的历史数据
    public List<Label> getLabelData(HistoryQueryCondition historyQueryCondition);

    //得到条形码数据的历史数据
    public List<Code> getCodeData(HistoryQueryCondition historyQueryCondition);

    //得到Led数据的历史数据
    public List<Led> getLedData(HistoryQueryCondition historyQueryCondition);

    //得到Logo数据的历史数据
    public List<Logo> getLogoData(HistoryQueryCondition historyQueryCondition);


    //得到插座数据的历史数据
    public List<Socket> getSocketData(HistoryQueryCondition historyQueryCondition);

    //得到aoi数据的历史数据
    public List<Aoi> getAoiData(HistoryQueryCondition historyQueryCondition);




    //得到标签数据的历史数据
    public int insertLabelData(String userId, String productModel
            , String sn, String orderNo, String testTime, String deviceLine
            , String deviceName);

    //得到条形码数据的历史数据
    public int insertCodeData(String userId, String deviceNo
            , String sn, String testTime, String deviceLine
            , String deviceName);

    //得到Led数据的历史数据
    public int insertLedData(String userId, String productModel
            , String useTime, String result, String modelFail, String testTime, String deviceLine
            , String deviceName);

    //得到Logo数据的历史数据
    public int insertLogoData(String userId, String productId
            , String ngCode, String testResult, String useTime, String testTime, String deviceLine
            , String deviceName);


    //得到插座数据的历史数据
    public int insertSocketData(String userId, String productModel
            , String ngCode, String testResult, String testTime, String sn, String deviceLine
            , String deviceName);

    //得到aoi数据的历史数据
    public int insertAoiData(String userId, String productModel
            , String ngCode, String testResult, String testTime, String sn, String deviceLine
            , String deviceName);


    public void insertExtraData(JSONObject data, String currentUser, String deviceType);

    public int insertExtraDataOneValue(String tableName, String fieldName, String value, int maxId);

    /******************************按月得到的接口***************************************************/

    //得到标签数据的历史数据
    public List<Label> getLabelDataByMonth(HistoryQueryCondition historyQueryCondition);

    //得到条形码数据的历史数据
    public List<Code> getCodeDataByMonth(HistoryQueryCondition historyQueryCondition);

    //得到Led数据的历史数据
    public List<Led> getLedDataByMonth(HistoryQueryCondition historyQueryCondition);

    //得到Logo数据的历史数据
    public List<Logo> getLogoDataByMonth(HistoryQueryCondition historyQueryCondition);


    //得到插座数据的历史数据
    public List<Socket> getSocketDataByMonth(HistoryQueryCondition historyQueryCondition);

    //得到aoi数据的历史数据
    public List<Aoi> getAoiDataByMonth(HistoryQueryCondition historyQueryCondition);



}
