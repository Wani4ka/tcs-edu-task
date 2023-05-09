package ru.tinkoff.edu.java.scrapper.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.commons.configuration.QueueProperties;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfiguration {

    private final QueueProperties properties;

    @Bean("queue")
    public Queue messagesQueue() {
        return QueueBuilder.durable(properties.getName())
                .deadLetterExchange(properties.getDeadLetterExchangeName())
                .build();
    }

    @Bean("exchange")
    public DirectExchange messagesExchange() {
        return new DirectExchange(properties.getName());
    }

    @Bean("binding")
    public Binding bindingMessages(@Qualifier("queue") Queue queue, @Qualifier("exchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(properties.getRoutingKey());
    }

    @Bean("dlx")
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(properties.getDeadLetterExchangeName());
    }

    @Bean("dlq")
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(properties.getDeadLetterQueueName()).build();
    }

    @Bean("dlb")
    public Binding deadLetterBinding(@Qualifier("dlq") Queue queue, @Qualifier("dlx") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(properties.getRoutingKey());
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
