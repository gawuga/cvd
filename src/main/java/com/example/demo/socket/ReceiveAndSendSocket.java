package com.example.demo.socket;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entiy.DeviceMac;
import com.example.demo.kafka.MyProducer;
import com.example.demo.service.DeviceService;
import com.example.demo.service.HistoryDataService;
import com.example.demo.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@MapperScan("com.example.demo.mapper")
@ServerEndpoint(value = "/online/{socketType}/{userno}/{dataType}")
@Component
public class ReceiveAndSendSocket {
    private static ApplicationContext applicationContext;

    private static DeviceService deviceService;

    private static UserService userService;

    private static HistoryDataService historyDataService;
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ReceiveAndSendSocket.applicationContext = applicationContext;
    }

    private static MyProducer myProducer = null;
    //初始化kafka对象
    private static ConcurrentHashMap<String, ReceiveAndSendSocket> webSocketSet = new ConcurrentHashMap<String, ReceiveAndSendSocket>();

    //实时统计通过量
    private static ConcurrentHashMap<String, Integer> passStatics = new ConcurrentHashMap<>();



    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    protected Session session;

    private String currentUserNum;
    private String currentDeviceType;

    private String currentUser;

    public static void setMyService(DeviceService myService,HistoryDataService historyService,UserService userS) {
        deviceService = myService;
        historyDataService = historyService;
        userService = userS;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam(value = "socketType") String socketType,@PathParam(value = "userno") String userno, @PathParam(value = "dataType") String dataType,Session session) throws Exception {
        this.session = session;
        currentUserNum = userno+socketType+dataType;
        webSocketSet.put(currentUserNum,this);
        addOnlineCount();           //在线数加1
            //
        if (!userService.isHaveUser(Integer.parseInt(userno))) {
            //为了不让没有订阅的用户连接
            throw new Exception("抛出没有此用户异常");
        }
        //为了防止恶意乱搞
        if (!socketType.equals("send")){
            if (!deviceService.getDeviceTypeList(Integer.parseInt(userno)).contains(dataType)){
                throw new Exception("抛出用户没有此设备异常");
            }
        }


        currentUser = userno;
        currentDeviceType = dataType;
            System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
            try {
                sendMessage("hello");
            } catch (IOException e) {
                System.out.println("IO异常");
            }


    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(currentUserNum);  //从set中删除
        //检测线停止 统计的数量就清零
        passStatics.remove(currentUserNum);
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */


    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {

        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        //防止发送格式外的数据

        //转换成json对象 取出datatype
        //转换成为JSONObject对象
        try{
            JSONObject jsonObj =JSONObject.parseObject(message);

            DeviceMac device = isHaveDevice(jsonObj.getString("deviceMac"));
            if (device == null) {
                sendMessage("你没有该设备 请先添加设备");
                return;
            }
            //插入数据
            insert(jsonObj.getJSONObject("data"),device.getDeviceLine(),device.getDeviceName());
            historyDataService.insertExtraData(jsonObj.getJSONObject("data"),currentUser,currentDeviceType);
            //获取该类型通过数
            int pass = getPass(jsonObj.getJSONObject("data"));
            JSONObject sendJson = getSendJson(jsonObj,pass,device);
            System.out.println(currentDeviceType+ "---pass----------" + pass);
            //网页端是否连上了socket 连接上就发数据
            clientIsConnect(sendJson);

        }catch (Exception e){
        System.out.println(e.getMessage());
        sendMessage("设备数据格式错误");
        }

    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ReceiveAndSendSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        ReceiveAndSendSocket.onlineCount--;
    }

    public void getAoiData(JSONObject data,String deviceLine
            , String deviceName){
        //testTime ,sn ,productModel ,testResult ,ngCode
        //根据属性名获取String数据;
        String testTime = data.getString("testTime");
        String sn = data.getString("sn");
        String productModel = data.getString("productModel");
        String testResult = data.getString("testResult");
        String ngCode = data.getString("ngCode");
        historyDataService.insertAoiData(currentUser,productModel,ngCode,testResult,testTime,sn,deviceLine
                ,deviceName);

        System.out.println("getAoiData");
        //System.out.println(dataType);

        //根据属性名获取用户编号;
        //String userNum = jsonObj.getString("userNum");
    }

    public void getSocketData(JSONObject data,String deviceLine
            , String deviceName){

        //testTime ,sn ,productModel ,testResult ,ngCode
        String testTime = data.getString("testTime");
        String sn = data.getString("sn");
        String productModel = data.getString("productModel");
        String testResult = data.getString("testResult");
        String ngCode = data.getString("ngCode");
        historyDataService.insertSocketData(currentUser,productModel,ngCode,testResult,testTime,sn,deviceLine
                ,deviceName);
        System.out.println("getSocketData");
    }

    public void getLabelData(JSONObject data,String deviceLine
            , String deviceName){
        //testTime ,sn,orderNo ,productModel
        String testTime = data.getString("testTime");
        String sn = data.getString("sn");
        String productModel = data.getString("productModel");
        String orderNo = data.getString("orderNo");
        historyDataService.insertLabelData(currentUser,productModel,sn,orderNo,testTime,deviceLine
                ,deviceName);
        System.out.println("getLabelData");
    }

    public void getLedData(JSONObject data,String deviceLine
            , String deviceName){
        //productModel ,result ,useTime ,modelFail ,testTime
        String testTime = data.getString("testTime");
        String useTime = data.getString("useTime");
        String productModel = data.getString("productModel");
        String result = data.getString("result");
        String modelFail = data.getString("modelFail");
        historyDataService.insertLedData(currentUser,productModel,useTime,result,modelFail,testTime,deviceLine
                ,deviceName);
        System.out.println("getLedData");
    }

    public void getLogoData(JSONObject data,String deviceLine
            , String deviceName){
        //productId ,Flag ,ngCode ,testResult ,useTime ,testTime
        String testTime = data.getString("testTime").trim();
        String useTime = data.getString("useTime").trim();
        String productId = data.getString("productId").trim();
        String testResult = data.getString("testResult").trim();
        String ngCode = data.getString("ngCode").trim();
        historyDataService.insertLogoData(currentUser,productId,ngCode,testResult,useTime,testTime,deviceLine
                ,deviceName);
        System.out.println("getLogoData");
    }

    public void getCodeData(JSONObject data,String deviceLine
            , String deviceName){
        //deviceNo ,testTime ,sn
        String testTime = data.getString("testTime");
        String sn = data.getString("sn");
        String deviceNo = data.getString("deviceNo");
        historyDataService.insertCodeData(currentUser,deviceNo,sn,testTime,deviceLine
                ,deviceName);
        System.out.println("getCodeData");
    }

    public void insert(JSONObject data,String deviceLine
            , String deviceName){
        switch (currentDeviceType) {
            case "led":
                getLedData(data,deviceLine
                        ,deviceName);
                break;
            case "logo":
                getLogoData(data,deviceLine
                        ,deviceName);
                break;
            case "label":
                getLabelData(data,deviceLine
                        ,deviceName);
                break;
            case "code":
                getCodeData(data,deviceLine
                        ,deviceName);
                break;
            case "aoi":
                getAoiData(data,deviceLine
                        ,deviceName);
                break;
            case "socket":
                getSocketData(data,deviceLine
                        ,deviceName);
                break;
        }
    }

    /**
     * 统计该类型的数量
     * @param data
     * @return
     */
    public int getPass(JSONObject data){

        int pass = passStatics.getOrDefault(currentUserNum,0);
        //统计通过数量
        if (getResult(data).equals("true")){
            pass++;
            passStatics.put(currentUserNum,pass);
        }
        return pass;
    }

    /**
     * 网页客户端是否连接上了
     * @param jsonObject
     * @throws IOException
     */
    public void clientIsConnect(JSONObject jsonObject) throws IOException {
        if (webSocketSet.get(currentUser+"senddevice")!=null){
            webSocketSet.get(currentUser+"senddevice").sendMessage(jsonObject.toString());
        }else {
            sendMessage("sendSocket未连接,网页无法接收数据");
            System.out.println("sendSocket未连接");
        }
    }

    /**
     * 筛选数据  展示给前端
     * @return
     */
    public JSONObject getSendJson(JSONObject jsonObject,int pass,DeviceMac deviceMac){
        JSONObject sjson = new JSONObject();
        sjson.put("result",getResult(jsonObject.getJSONObject("data")));
        sjson.put("deviceId",jsonObject.getString("deviceMac"));
        Date time = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String current = sdf.format(time);
        sjson.put("line",deviceMac.getDeviceLine());
        sjson.put("deviceName",deviceMac.getDeviceName());
        //被测物id
        if (currentDeviceType=="code")sjson.put("dataId",jsonObject.getJSONObject("data").getString("deviceNo"));
        else sjson.put("dataId",jsonObject.getJSONObject("data").getString("productModel"));
        sjson.put("date",current.split(" ")[1]);
        sjson.put("pass",pass);
        sjson.put("dataType",currentDeviceType);
        return sjson;
    }

    /**
     * 得到通过结果
     * @param data
     * @return
     */
    public String getResult(JSONObject data){
        String temp = data.getString("result");
        String result = temp==null?data.getString("testResult"):temp;
        result=result==null?"":result;
        return result;
    }

    /**
     * 是否有该设备
     * @param mac
     * @return
     */
    public DeviceMac isHaveDevice(String mac){
        List<DeviceMac> d = deviceService.getDeviceMacByDeviceType(currentDeviceType,Integer.parseInt(currentUser));

        for (DeviceMac dm:d){
            if (dm.getDeviceMac().equals(mac))

                    return dm;
        }
        return null;
    }

}
