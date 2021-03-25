package com.example.demo.kafka;


import com.example.demo.socket.SendSocket;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class MyConsumer  implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private Properties props = getProperties();
    private final String TOPIC = "LBW_EPM"; //消费者主题 和消费者主题一样（"LBW_EPM"）
    private SendSocket socket;
    private String topic;

    public SendSocket getSocket() {
        return socket;
    }

    public void setSocket(SendSocket socket) {
        this.socket = socket;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    //从kafka里面消费信息
    public void getMsg(String topic, SendSocket socket) {
        //创建消息者实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //订阅topic1的消息
        consumer.subscribe(Arrays.asList(topic));
        try {
            while (true) {
                Thread.sleep(1000);
                //到服务器中读取记录
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {

                    System.out.println("value:" + record.value());
                    //发送到手机端
                    System.out.println(socket);
                    socket.sendMessage(record.value());
                    System.out.println("发送成功");
                }
                System.out.println(socket+":等待数据来中");
            }
        } catch (InterruptedException | IOException e) {
            logger.error("MyConsumer error:", e);
        } finally {
            consumer.close();
        }

    }

    private static Properties getProperties() {
        //配置信息
        Properties props = new Properties();
        //kafka服务器地址
        props.put("bootstrap.servers", "192.168.248.137:9092");
        //必须指定消费者组 "DEMO" 自定义任何值/可以选择已有的消费者组
        props.put("group.id", "DEMO");
        //设置数据key和value的序列化处理类
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
       props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        return props;
    }

    @Override
    public void run() {
        this.getMsg(this.topic,this.socket);
    }
}
