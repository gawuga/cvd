package com.example.demo.socket;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.MySocket;
import com.example.demo.kafka.MyProducer;
import org.springframework.stereotype.Component;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * 接收设备数据的socket
 */
@ServerEndpoint(value = "/device")
@Component
public class ReceiveSocket extends MySocket {
    //六个消息变量名
    private static final String LED = "led";
    private static final String LABEL = "label";
    private static final String LOGO = "logo";
    private static final String CODE = "code";
    private static final String SOCKET = "socket";
    private static final String AOI = "AOI";
    private static MyProducer myProducer = null;
    //初始化kafka对象

    public ReceiveSocket() {
        myProducer = MyProducer.getInstance();
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        //转换成json对象 取出datatype
        //转换成为JSONObject对象
        JSONObject jsonObj =JSONObject.parseObject(message);

        //根据属性名获取String数据;
        String dataType = jsonObj.getString("dataType");
        System.out.println(dataType);
        //判断用户是否拥有该设备类型--------
        /***
         *   //TODO---从单点系统中加载用户数据
         *         User u = new User();
         *         if ( u.getDevices().contains(dataType)){
         *             //放到kafka里面 dataType 代表主题的类型
         *             myProducer.send(dataType,message);
         *         }else {
         *             this.sendMessage("数据设备类型错误");
         *         }
         */

    }

}
