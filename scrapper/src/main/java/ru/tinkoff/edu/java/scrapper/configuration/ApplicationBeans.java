package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.commons.configuration.QueueProperties;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.function.Function;

@Component
public class ApplicationBeans {
    @Bean("scheduler")
    public Scheduler scheduler(ApplicationConfig config) {
        return config.scheduler();
    }

    @Bean("queueProperties")
    public QueueProperties queueProperties(ApplicationConfig config) {
        return config.queue();
    }

    @Bean("reverse")
    public Function<Duration, OffsetDateTime> reverse() {
        return duration -> OffsetDateTime.now().minus(duration == null ? Duration.ZERO : duration);
    }
}
