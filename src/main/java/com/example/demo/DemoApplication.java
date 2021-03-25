package com.example.demo;


import com.example.demo.service.DeviceService;
import com.example.demo.service.HistoryDataService;
import com.example.demo.service.UserService;
import com.example.demo.socket.ReceiveAndSendSocket;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan("com.example.demo.mapper")
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(DemoApplication.class, args);

        // 获取Spring IOC容器中的Service并注入
        DeviceService myService = configurableApplicationContext.getBean(DeviceService.class);
        HistoryDataService dataService = configurableApplicationContext.getBean(HistoryDataService.class);
        UserService userService = configurableApplicationContext.getBean(UserService.class);
        ReceiveAndSendSocket.setMyService(myService,dataService,userService);

    }

    /**
     *  @Bean
     *     public IoAcceptor ioAcceptor() throws Exception {
     *         int PORT = 8086;
     *         IoAcceptor acceptor=new NioSocketAcceptor();
     *         acceptor.setHandler(new ServerHandler());
     *         acceptor.getSessionConfig().setReadBufferSize(1024);
     *         acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 100);
     *         acceptor.bind(new InetSocketAddress(PORT));
     *         System.out.println("服务器在端口：" + PORT + "已经启动");
     *         return acceptor;
     *     }
     */
}
