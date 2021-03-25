package com.example.demo;

import org.springframework.stereotype.Component;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSoket extends MySocket{
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //群发消息
        for (MySocket item : this.webSocketSet) {
            try {
                item.sendMessage("logo");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
