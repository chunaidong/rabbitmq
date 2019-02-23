package com.wangchun.rabbitmq.rabbitmqtest.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class Producer {

    public static void main(String[] args) throws Exception{
        //1.创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.101.203.238");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        //2.获取Connection
        Connection connection = connectionFactory.newConnection();
        //3.通过Connection创建一个Channel
        Channel channel = connection.createChannel();
        //4.指定消费的投递模式：消息的确认模式
        channel.confirmSelect();
        //5.声明数据
        String exchangName="test_consumer_exchange";
        String routingKey = "consumer.save";
        //6.发送数据
        String msg = "Hello Consumer 4 Consumer Self";
        channel.basicPublish(exchangName, routingKey, null, msg.getBytes());

    }
}
