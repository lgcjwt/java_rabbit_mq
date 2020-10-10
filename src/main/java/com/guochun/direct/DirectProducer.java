package com.guochun.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author logicer
 *
 * direct 直连类型的exchange
 */
public class DirectProducer {
    private final static String EXCHANGE_NAME = "direct_exchange";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri("amqp://guest:guest@127.0.0.1:5672");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        for(int i = 0; i < 10; i ++){
            channel.basicPublish(EXCHANGE_NAME, "guochun.test", null, "测试 direct_exchange ".getBytes());
        }

        channel.close();
        connection.close();
    }
}
