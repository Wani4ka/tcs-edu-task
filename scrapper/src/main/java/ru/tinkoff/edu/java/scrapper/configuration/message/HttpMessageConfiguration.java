package ru.tinkoff.edu.java.scrapper.configuration.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.client.BotClient;
import ru.tinkoff.edu.java.scrapper.service.BotService;
import ru.tinkoff.edu.java.scrapper.service.HttpBotService;
import ru.tinkoff.edu.java.scrapper.service.SubscriptionService;

@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "false")
public class HttpMessageConfiguration {
    public HttpMessageConfiguration() {
        log.info("Using HTTP for messaging");
    }

    @Bean
    public BotService botService(BotClient client, SubscriptionService subscriptions) {
        return new HttpBotService(client, subscriptions);
    }
}
