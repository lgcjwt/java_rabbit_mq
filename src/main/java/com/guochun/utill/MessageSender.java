package com.guochun.utill;


import com.rabbitmq.client.Channel;

import java.time.LocalDateTime;

/**
 * @author logicer
 *
 * 实现Runnable 可以在producer中多线程生成消息
 */
public class MessageSender implements Runnable {

    private Channel channel;
    private String msg;
    private String exchangeName;
    private String routingKey;
    private String timestamp;


    public MessageSender(Channel channel, String msg, String exchangeName, String routingKey){
        this.channel = channel;
        this.msg = msg;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.timestamp = String.valueOf(LocalDateTime.now());
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(i + " routingKey: " + routingKey + " msg: " + msg + timestamp);
                channel.basicPublish(exchangeName, routingKey, null, (i + msg + timestamp).getBytes());
            }
            channel.close();
            channel.getConnection().close();
        } catch (Exception e){
            System.out.println(e);
        }

    }
}
