package com.wangchun.rabbitmq.rabbitmqtest.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

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
        String exchangeName="test_confirm_exchange";
        String exchangeType = "topic";
        String routingKey = "confirm.#";
        String queueName = "test_confirm_queue";
        //4.声明交换机，队列然后进行绑定
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);
        //5.创建消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);
        while (true){
            Delivery delivery =  consumer.nextDelivery();
            System.out.println(new String(delivery.getBody()));
        }
    }

}
