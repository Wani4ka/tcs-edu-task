package ru.tinkoff.edu.java.scrapper.configuration.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.service.BotService;
import ru.tinkoff.edu.java.scrapper.service.QueueBotService;
import ru.tinkoff.edu.java.scrapper.service.ScrapperQueueProducer;
import ru.tinkoff.edu.java.scrapper.service.SubscriptionService;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "true")
public class QueueMessageConfiguration {
    public QueueMessageConfiguration() {
        log.info("Using queue for messaging");
    }

    @Bean
    public BotService botService(ScrapperQueueProducer queue, SubscriptionService subscriptions) {
        return new QueueBotService(queue, subscriptions);
    }
}
