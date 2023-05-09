package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.commons.configuration.QueueProperties;

@Component
public class ApplicationBeans {
    @Bean("queueProperties")
    public QueueProperties queueProperties(ApplicationConfig config) {
        return config.queue();
    }
}
