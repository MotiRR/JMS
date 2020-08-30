package com.geekbrains.book.store.configs;

import com.geekbrains.book.store.services.RabbitMQReceiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sound.midi.Receiver;

@Configuration
public class RabbitmqConfig {

    @Value("${rabbitmq.queue_handling}")
    String topicExchangeName;

    @Value("${rabbitmq.queue_handling}")
    String queueName;

    @Value("${rabbitmq.queue_done}")
    String queueReceiver;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueReceiver);
        container.setMessageListener(listenerAdapter);
        container.setReceiveTimeout(5000);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(RabbitMQReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
