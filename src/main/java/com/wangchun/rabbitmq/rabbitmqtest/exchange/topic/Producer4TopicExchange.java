package com.wangchun.rabbitmq.rabbitmqtest.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer4TopicExchange {


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
        String exchangeName = "test_topic_exchange";
        String routingKeyA="user.a";
        String routingKeyB="user.b";
        String routingKeyC="user.a.b";
        String msg = "Hello World 4 Direct Exchange";
        channel.basicPublish(exchangeName, routingKeyA, null, msg.getBytes());
        channel.basicPublish(exchangeName, routingKeyB, null, msg.getBytes());
        channel.basicPublish(exchangeName, routingKeyC, null, msg.getBytes());
        channel.close();
        connection.close();



    }

}
