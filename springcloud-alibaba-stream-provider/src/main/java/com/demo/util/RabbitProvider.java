package com.demo.util;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class RabbitProvider {
    //队列名称
    private static final String QUEUE_NAME = "test_simple_queue";

    public static Connection getConnection(){
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        // 地址
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/");

        factory.setUsername("guest");
        factory.setPassword("guest");
        // 建立连接
        Connection conn  = null;
        try {
            conn = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return  conn;
    }


    // 测试rabbitmq 发布
    public static void publish() throws IOException, TimeoutException {
        //获取连接
        Connection connection = getConnection();
        //从连接中获取一个通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "This is simple queue from hello world";
        //发送消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("utf-8"));
        System.out.println("[send]：" + message);
        channel.close();
        connection.close();
    }



    public static void main(String[] args) throws IOException, TimeoutException {
        publish();
    }
}
