package com.guochun.topic;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author logicer
 */
public class TopicBConsumer {
    private final static String EXCHANGE_NAME = "topic_exchange";
    private final static String QUEUE_NAME = "topic_queue_b";

    public static void main(String[] args) throws Exception {
        String uri = "amqp://guest:guest@127.0.0.1:5672";
        //String routingKey = "topic.b.*";
        String routingKey = "topic.*.*";
        Channel channel = RabbitConnection.getMyChannel(uri, EXCHANGE_NAME, "topic", QUEUE_NAME, routingKey);

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException
            {
                String msg = new String(body, "UTF-8");
                System.out.println("queue B message is : " + msg);
            }
        };

        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
