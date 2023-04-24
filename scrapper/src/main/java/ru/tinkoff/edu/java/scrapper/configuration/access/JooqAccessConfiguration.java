package ru.tinkoff.edu.java.scrapper.configuration.access;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.configuration.Scheduler;
import ru.tinkoff.edu.java.scrapper.domain.repository.jooq.JooqChatRepository;
import ru.tinkoff.edu.java.scrapper.domain.repository.jooq.JooqLinkRepository;
import ru.tinkoff.edu.java.scrapper.domain.repository.jooq.JooqSubscriptionRepository;
import ru.tinkoff.edu.java.scrapper.service.*;
import ru.tinkoff.edu.java.scrapper.service.jooq.JooqChatService;
import ru.tinkoff.edu.java.scrapper.service.jooq.JooqLinkService;
import ru.tinkoff.edu.java.scrapper.service.jooq.JooqLinkUpdater;
import ru.tinkoff.edu.java.scrapper.service.jooq.JooqSubscriptionService;

@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jooq")
public class JooqAccessConfiguration {

    public JooqAccessConfiguration() {
        log.info("Using JOOQ access type");
    }

    @Bean
    public LinkService linkService(JooqLinkRepository repository) {
        return new JooqLinkService(repository);
    }

    @Bean
    public ChatService chatService(JooqChatRepository repository) {
        return new JooqChatService(repository);
    }

    @Bean
    public SubscriptionService subscriptionService(JooqSubscriptionRepository repository, LinkService linkService) {
        return new JooqSubscriptionService(repository, linkService);
    }

    @Bean
    public LinkUpdater linkUpdater(JooqLinkRepository repository, Scheduler scheduler, BotService botService, GitHubService ghService, StackOverflowService soService) {
        return new JooqLinkUpdater(repository, scheduler, botService, ghService, soService);
    }
}
