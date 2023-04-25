package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.dto.LinkUpdateRequest;

import java.util.Map;

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
