package com.geekbrains.book.store.services;

import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.entities.User;
import com.geekbrains.book.store.exceptions.ResourceNotFoundException;
import com.geekbrains.book.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private RabbitMQSender rabbitMQSender;

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order with id: " + id + " not found"));
    }

    public Order saveOrUpdate(Order order) {
        return orderRepository.save(order);
    }

    public void createOrder(User user, List<OrderItem> orderItems) {
        Order newOrder = new Order(user);
        for (OrderItem orderItem: orderItems){
            orderItem.setOrder(newOrder);
        }
        newOrder.setOrderItems(orderItems);
        newOrder.setStatus("В обработке");
        saveOrUpdate(newOrder);
        rabbitMQSender.send(newOrder);
        orderItems.clear();
    }
}
