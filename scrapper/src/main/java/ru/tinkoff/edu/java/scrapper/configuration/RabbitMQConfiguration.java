package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    @Bean
    public DirectExchange directExchange(ApplicationConfig config) {
        return new DirectExchange(config.queue().getExchangeName());
    }

    @Bean
    public Queue queue(ApplicationConfig config) {
        return new Queue(config.queue().getQueueName());
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange, ApplicationConfig config) {
        return BindingBuilder.bind(queue).to(exchange).with(config.queue().getRoutingKey());
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
