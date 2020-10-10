package com.guochun.topic;

import com.guochun.utill.MessageSender;
import com.rabbitmq.client.Channel;

/**
 * @author logicer
 */
public class TopicProducer {
    private final static String EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] args) throws Exception {
        String uri = "amqp://guest:guest@127.0.0.1:5672";
        Channel channelA = RabbitConnection.getProduceChannel(uri);
        Channel channelB = RabbitConnection.getProduceChannel(uri);

        String msgA = " topicA msg AAAAAAA ";
        String msgB = " topicB msg BBBBBBB ";

        MessageSender messageSenderA = new MessageSender(channelA, msgA, EXCHANGE_NAME, "topic.a.ddd");
        MessageSender messageSenderB = new MessageSender(channelB, msgB, EXCHANGE_NAME, "topic.b.ddd");

        Thread threadA = new Thread(messageSenderA);
        Thread threadB = new Thread(messageSenderB);

        System.out.println("开始执行发送！");
        threadA.start();
        threadB.start();

    }
}
