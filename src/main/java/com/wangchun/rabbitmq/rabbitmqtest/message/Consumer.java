package com.wangchun.rabbitmq.rabbitmqtest.message;

import com.rabbitmq.client.*;
import com.rabbitmq.client.QueueingConsumer.Delivery;

import java.util.HashMap;
import java.util.Map;

public class Consumer {

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

        //4.声明一个消息队列
        String queueName = "test";
        channel.queueDeclare(queueName, true, false, false, null);

        //5.创建消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //6.设置消费者
        channel.basicConsume(queueName, true, consumer);



        while (true){
            //7.获取信息
            Delivery delivery = consumer.nextDelivery();
            System.out.println(new String(delivery.getBody()));
            AMQP.BasicProperties properties = delivery.getProperties();
            System.out.println(properties.getHeaders().get("mykey1"));
            System.out.println(properties.getHeaders().get("mykey2"));

        }

    }

}
