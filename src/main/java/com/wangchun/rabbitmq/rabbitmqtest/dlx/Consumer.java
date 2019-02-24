package com.wangchun.rabbitmq.rabbitmqtest.dlx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

public class Consumer {
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
        String exchangeName="test_dlx_exchange";
        String exchangeType = "topic";
        String routingKey = "dlx.#";
        String queueName = "test_dlx_queue";
        //4.声明交换机，队列然后进行绑定
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        Map<String,Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "dlx.exchange");
        channel.queueDeclare(queueName, true, false, false, arguments);
        channel.queueBind(queueName, exchangeName, routingKey);
        //声明死信队列
        channel.exchangeDeclare("dlx.exchange", "topic", true, false, false, null);
        channel.queueDeclare("dlx.queue", true, false, false, null);
        channel.queueBind("dlx.queue", "dlx.exchange", "#");


        channel.basicConsume(queueName, true, new MyConsumer(channel));

    }

}
