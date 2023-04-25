package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    @Bean("queue")
    public Queue messagesQueue(ApplicationConfig config) {
        return QueueBuilder.durable(config.queue().getName())
                .deadLetterExchange(config.queue().getName() + ".dlx")
                .build();
    }

    @Bean("exchange")
    public DirectExchange messagesExchange(ApplicationConfig config) {
        return new DirectExchange(config.queue().getName());
    }

    @Bean("binding")
    public Binding bindingMessages(@Qualifier("queue") Queue queue, @Qualifier("exchange") DirectExchange exchange, ApplicationConfig config) {
        return BindingBuilder.bind(queue).to(exchange).with(config.queue().getRoutingKey());
    }

    @Bean("dlx")
    public FanoutExchange deadLetterExchange(ApplicationConfig config) {
        return new FanoutExchange(config.queue().getName() + ".dlx");
    }

    @Bean("dlq")
    public Queue deadLetterQueue(ApplicationConfig config) {
        return QueueBuilder.durable(config.queue().getName() + ".dlq").build();
    }

    @Bean("dlb")
    public Binding deadLetterBinding(@Qualifier("dlq") Queue queue, @Qualifier("dlx") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
