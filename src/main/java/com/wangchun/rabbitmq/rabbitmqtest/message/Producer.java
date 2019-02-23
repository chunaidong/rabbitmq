package com.wangchun.rabbitmq.rabbitmqtest.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

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

        Map<String,Object> map = new HashMap();
        map.put("mykey1", 111);
        map.put("mykey2", 222);
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .deliveryMode(2)
                //超市时间
                .expiration("10000")
                .headers(map)
                .build();
        //4.通过Channel发送消息

        for(int i = 0 ;i<10;i++){
            channel.basicPublish("", "test", properties, "hello word".getBytes());
        }

        channel.close();
        connection.close();



    }

}
