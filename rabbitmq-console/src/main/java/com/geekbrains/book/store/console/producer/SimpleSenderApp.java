package com.geekbrains.book.store.console.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SimpleSenderApp {
    private ConnectionFactory factory;
    private final static String QUEUE_NAME = "order_done";

    public SimpleSenderApp() {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
    }

    public void send(String message) {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
