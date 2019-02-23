package com.wangchun.rabbitmq.rabbitmqtest.quickstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {


    public static void main(String[] args) throws Exception{

        //1.创建一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.101.203.238");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        //2.创建一个连接
        Connection connection = connectionFactory.newConnection();

        //3.创建一个通信
        Channel channel = connection.createChannel();

        //4.通过Channel发送消息

        for(int i = 0 ;i<10;i++){
            channel.basicPublish("", "test", null, "hello word".getBytes());
        }

        channel.close();
        connection.close();



    }

}
