package com.wangchun.rabbitmq.rabbitmqtest.consumer;

import com.rabbitmq.client.*;
import com.rabbitmq.client.Consumer;

import java.io.IOException;

public class MyConsumer extends DefaultConsumer {


    public MyConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("-----------consumer-------");
        System.out.println("consumerTag: "+consumerTag);
        System.out.println("envelope: "+envelope);
        System.out.println("properties: "+properties);
        System.out.println("body: "+new String(body));
    }
}
