package com.example.demo.socket;

import com.example.demo.kafka.MyConsumer;
import com.example.demo.kafka.MyProducer;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 发给手机的socket
 */
@ServerEndpoint(value = "/online/{userno}")
@Component
public class SendSocket  {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    private static MyProducer myProducer = null;
    //初始化kafka对象
    private static ConcurrentHashMap<String, SendSocket> webSocketSet = new ConcurrentHashMap<String, SendSocket>();
    private Session session;

    public SendSocket() {
        myProducer = MyProducer.getInstance();
    }
    private String userno;

    @OnOpen
    public void onOpen(@PathParam(value = "userno") String param, Session session) {
        System.out.println(param);
        this.session = session;
        userno = param;//接收到发送消息的人员编号

        webSocketSet.put(param, this);//加入map中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        SendSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        SendSocket.onlineCount--;
    }

    /**
     * 前端给的message 设定了是 led 或者其他设备类型的字符串
     * @param message
     * @param session
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        MyConsumer consumer = new MyConsumer();

        System.out.println(message);
        String[] t = message.split(",");
        String user = t[0];
        String datatype = t[1];
        //从kafka中消费数据 并且发送到手机端
        consumer.setSocket(webSocketSet.get(user));
        consumer.setTopic(datatype);
        Thread cs = new Thread(consumer);
        cs.start();

    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
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
}
