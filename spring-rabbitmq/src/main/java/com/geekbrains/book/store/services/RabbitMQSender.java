package com.geekbrains.book.store.services;

import com.geekbrains.book.store.entities.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue_handling}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    public void send(Order order) {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(exchange, "foo.bar.baz", order.getId().toString());
    }
}
