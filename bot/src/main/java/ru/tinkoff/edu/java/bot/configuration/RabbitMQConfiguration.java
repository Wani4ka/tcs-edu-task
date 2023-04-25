package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.dto.LinkUpdateRequest;

import java.util.Map;

@Configuration
public class RabbitMQConfiguration {
    @Bean("exchange")
    public DirectExchange messagesExchange(ApplicationConfig config) {
        return new DirectExchange(config.queue().getName());
    }

    @Bean("dlx")
    public FanoutExchange deadLetterExchange(ApplicationConfig config) {
        return new FanoutExchange(config.queue().getName() + ".dlx");
    }

    @Bean("queue")
    public Queue messagesQueue(ApplicationConfig config) {
        return QueueBuilder.durable(config.queue().getName())
                .deadLetterExchange("")
                .deadLetterRoutingKey(config.queue().getRoutingKey())
                .build();
    }

    @Bean("dlq")
    public Queue dlqQueue(ApplicationConfig config) {
        return QueueBuilder.durable(config.queue().getName() + ".dlq").build();
    }

    @Bean("binding")
    public Binding bindingMessages(@Qualifier("queue") Queue queue, @Qualifier("exchange") DirectExchange exchange, ApplicationConfig config) {
        return BindingBuilder.bind(queue).to(exchange).with(config.queue().getRoutingKey());
    }

    @Bean("dlb")
    public Binding deadLetterBinding(@Qualifier("dlq") Queue queue, @Qualifier("dlx") FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public ClassMapper classMapper() {
        var classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("ru.tinkoff.edu.java.scrapper.dto.*");
        classMapper.setIdClassMapping(Map.of("ru.tinkoff.edu.java.scrapper.dto.LinkUpdateRequest", LinkUpdateRequest.class));
        return classMapper;
    }

    @Bean
    public MessageConverter jsonMessageConverter(ClassMapper classMapper) {
        var jsonConverter = new Jackson2JsonMessageConverter();
        jsonConverter.setClassMapper(classMapper);
        return jsonConverter;
    }
}
