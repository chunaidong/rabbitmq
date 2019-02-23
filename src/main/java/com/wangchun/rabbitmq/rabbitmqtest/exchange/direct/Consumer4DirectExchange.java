package com.wangchun.rabbitmq.rabbitmqtest.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class Consumer4DirectExchange {

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

        //声明
        String exchangeName = "test_direct_exchange";
        String exchangeType ="direct";
        String queueName = "test_direct_queue";
        String routingKey="test.dircet";
        //声明交换机
        channel.exchangeDeclare(exchangeName, exchangeType, true,false,false,null);
        //声明一个消息队列
        channel.queueDeclare(queueName, true, false, false, null);
        //建立一个绑定关系
        channel.queueBind(queueName, exchangeName, routingKey);
        //5.创建消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //6.设置消费者
        channel.basicConsume(queueName, true, consumer);

        while (true){
            //7.获取信息
            Delivery delivery = consumer.nextDelivery();
            System.out.println("收到消息："+new String(delivery.getBody()));

        }

    }

}
