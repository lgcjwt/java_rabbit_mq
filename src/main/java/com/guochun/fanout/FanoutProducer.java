package com.guochun.fanout;

import com.guochun.topic.RabbitConnection;
import com.guochun.utill.MessageSender;
import com.rabbitmq.client.Channel;

/**
 * @author logicer
 * 广播类型的exchange，复用queue，topic_queue_b和topic_queue_a
 */
public class FanoutProducer {
    private final static String EXCHANGE_NAME = "fanout_exchange";

    public static void main(String[] args) throws Exception {
        String uri = "amqp://guest:guest@127.0.0.1:5672";
        Channel channel = RabbitConnection.getProduceChannel(uri);
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout", false, false, null);
        channel.queueBind("topic_queue_b", EXCHANGE_NAME, "test");
        channel.queueBind("topic_queue_a", EXCHANGE_NAME, "test");

        String msg = "This is a fanout MSG";

        MessageSender messageSender = new MessageSender(channel, msg, EXCHANGE_NAME, "test");

        messageSender.run();
    }
}
