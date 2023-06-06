package ru.tinkoff.edu.java.bot.configuration;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.commons.configuration.QueueProperties;

@Component
public class ApplicationBeans {
    @Bean("queueProperties")
    public QueueProperties queueProperties(ApplicationConfig config) {
        return config.queue();
    }

    @Bean("updatesCounter")
    public Counter updatesCounter(MeterRegistry meterRegistry) {
        return meterRegistry.counter("updates_count");
    }
}
