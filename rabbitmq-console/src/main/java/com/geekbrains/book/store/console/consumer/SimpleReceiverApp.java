package com.geekbrains.book.store.console.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleReceiverApp implements Runnable {
    private final static String QUEUE_NAME = "order_handling";
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private AtomicBoolean atomicBoolean;
    private ConcurrentHashMap<String, String> map;

    public SimpleReceiverApp() throws Exception {
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
        atomicBoolean = new AtomicBoolean(true);
        map = new ConcurrentHashMap<>();
    }

    private void stop() {
        atomicBoolean.set(false);
    }

    public ConcurrentHashMap<String, String> getMap() {
        return map;
    }

    @Override
    public void run() {
        while (atomicBoolean.get()) {
            try {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);

                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                    map.put(message, message);
                };

                channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
                });
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
