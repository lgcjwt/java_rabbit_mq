package com.guochun.direct;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author logicer
 */
public class DirectConsumer {
    private final static String EXCHANGE_NAME = "direct_exchange";
    private final static String QUEUE_NAME = "direct_queue";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setUri("amqp://guest:guest@127.0.0.1:5672");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct", false, false, null);

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "guochun.test");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException
            {
                String msg = new String(body, "UTF-8");
                System.out.println("message is : " + msg);
            }
        };

        channel.basicConsume(QUEUE_NAME, true, consumer);

    }
}
