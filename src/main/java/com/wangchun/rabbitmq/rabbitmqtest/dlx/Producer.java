package com.wangchun.rabbitmq.rabbitmqtest.dlx;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

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
        //5.声明数据
        String exchangName="test_dlx_exchange";
        String routingKey = "dlx.save";
        //6.发送数据
        String msg = "Hello Consumer 4 Consumer Self";
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .deliveryMode(2)
                .contentEncoding("utf-8")
                .expiration("5000")
                .build();
        channel.basicPublish(exchangName, routingKey,true, properties, msg.getBytes());

    }
}
