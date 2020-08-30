package com.geekbrains.book.store.services;

import com.geekbrains.book.store.entities.Order;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitMQReceiver {

    private RabbitTemplate rabbitTemplate;
    private OrderService orderService;

    public void receiveMessage(byte[] message) {
        String idStr = new String(message);
        long idOrder = Long.parseLong(idStr);
        Order order = orderService.getOrderById(idOrder);
        System.out.println("Order <" + idOrder + ">" + order.getStatus());
        order.setStatus("Готово");
        orderService.saveOrUpdate(order);
        System.out.println("Order <" + idOrder + ">" + order.getStatus());
    }
}
