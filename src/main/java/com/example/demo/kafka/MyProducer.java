package com.example.demo.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.RetriableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class MyProducer {
    private final static Logger logger = LoggerFactory.getLogger(MyProducer.class);

    private String TOPIC = "flink"; //kafka主题 把消息发布到这个主题 自定义

    private Producer<String, String> msgProducer = null;
    private Properties properties = MyProperties.getProperties();//kafka消费者配置
    private static MyProducer producer = new MyProducer();

    public static MyProducer getInstance() {  //单例
        if (producer == null) {
            producer = new MyProducer();
        }
        return producer;
    }
    public void initProducer(){
        msgProducer = new KafkaProducer<String, String>(properties);
    }

    public void send(String topic,String msg) {

        //KafkaData data = new KafkaData(0);
        //List<Map> listMap = new ArrayList<Map>();
       // Map map = new HashMap();
        //map.put("keyTest", "我是测试消息");
       // listMap.add(map);
       // data.setListMap(listMap);
        //{"userID": "user_3", "eventTime": "2019-08-17 12:19:47", "eventType": "browse", "productID": 1}
        //{"userID": "user_2", "eventTime": "2019-08-17 12:19:48", "eventType": "click", "productID": 1}

        msgProducer = new KafkaProducer<String, String>(properties);
        ProducerRecord record = new ProducerRecord<String, String>(topic, msg);

        msgProducer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    logger.info("kafka send successful");
                } else {
                    //对kafka可重试异常，消息第一次发送失败再次重试。
                    if (e instanceof RetriableException) {
                        //处理可重试异常
                        try {
                            logger.error("kafka send fail Retry sending.");
                            Thread.sleep(3000);
                            //jMyProducer.getInstance().send();
                        } catch (InterruptedException e1) {
                            logger.error("kafka error :", e1);
                        }
                    } else {//对于kafka来说，不可重试异常，抛出。
                        throw new KafkaException("kafka server message error.");
                    }
                }
            }
        });

        msgProducer.close();
    }

    public void closeProducer(){
        //关闭 以免造成资源泄露
        msgProducer.close();
    }

}

//内部类的使用
class MyProperties {
    protected static String IP = "192.168.248.137";//kafka 消息系统 IP
    protected static String PORT = "9092";   //kafka端口号
    public static Properties getProperties() {
        Properties props = new Properties();
        //集群地址，多个服务器用 逗号 ","分隔
        props.put("bootstrap.servers", IP + ":" + PORT);
        //key 的序列化，此处以字符串为例，使用kafka已有的序列化类
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //发送字符串可以使用StringSerializer这个序列化器
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //使用自定义序列化器 发送自定义数据/json数据
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("request.required.acks", "1");
        return props;
    }
}
