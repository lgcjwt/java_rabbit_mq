package com.guochun.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author logicer
 */
public class RabbitConnection {
    public static Channel getMyChannel(String uri, String exchangeName, String type, String queueName, String routingKey) throws Exception{
        //1
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri(uri);
        //2
        Connection connection = connectionFactory.newConnection();
        //3
        Channel channel =  connection.createChannel();
        //4
        channel.exchangeDeclare(exchangeName, type, false, false, null);
        channel.queueDeclare(queueName, false,false,false, null);
        //5
        channel.queueBind(queueName, exchangeName, routingKey);

        return channel;
    }

    public static Channel getProduceChannel(String uri) throws Exception{
        //1
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri(uri);
        //2
        Connection connection = connectionFactory.newConnection();
        //3
        Channel channel =  connection.createChannel();

        return channel;
    }
}
