package com.demo.util;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitConsumer {

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


    // 测试rabbitmq 消费
    public static void consumer () throws IOException, TimeoutException {
        //获取连接
        Connection connection = getConnection();
        //从连接中获取一个通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("waiting for messages.");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"UTF-8");
                System.out.println(envelope.getExchange()+","+envelope.getRoutingKey()+","+msg);
            }
        };

        //监听队列
        // channel绑定队列，autoAck为true表示一旦收到消息则自动回复确认消息
        channel.basicConsume(QUEUE_NAME, true, consumer);

    }



    public static void main(String[] args) throws IOException, TimeoutException {
        consumer();
    }
}
