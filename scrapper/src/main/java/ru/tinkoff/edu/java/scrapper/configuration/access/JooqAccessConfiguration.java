package ru.tinkoff.edu.java.scrapper.configuration.access;

import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.configuration.Scheduler;
import ru.tinkoff.edu.java.scrapper.domain.repository.jooq.JooqChatRepository;
import ru.tinkoff.edu.java.scrapper.domain.repository.jooq.JooqLinkRepository;
import ru.tinkoff.edu.java.scrapper.domain.repository.jooq.JooqSubscriptionRepository;
import ru.tinkoff.edu.java.scrapper.service.*;
import ru.tinkoff.edu.java.scrapper.service.external.GitHubService;
import ru.tinkoff.edu.java.scrapper.service.external.StackOverflowService;
import ru.tinkoff.edu.java.scrapper.service.jooq.JooqChatService;
import ru.tinkoff.edu.java.scrapper.service.jooq.JooqLinkService;
import ru.tinkoff.edu.java.scrapper.service.jooq.JooqSubscriptionService;

@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jooq")
public class JooqAccessConfiguration {

    public JooqAccessConfiguration() {
        log.info("Using JOOQ access type");
    }

    @Bean
    public JooqLinkRepository linkRepository(DSLContext context) {
        return new JooqLinkRepository(context);
    }

    @Bean
    public LinkService linkService(JooqLinkRepository repository) {
        return new JooqLinkService(repository);
    }

    @Bean
    public JooqChatRepository chatRepository(DSLContext context) {
        return new JooqChatRepository(context);
    }

    @Bean
    public ChatService chatService(JooqChatRepository repository) {
        return new JooqChatService(repository);
    }

    @Bean
    public JooqSubscriptionRepository subscriptionRepository(DSLContext context) {
        return new JooqSubscriptionRepository(context);
    }

    @Bean
    public SubscriptionService subscriptionService(JooqSubscriptionRepository repository, LinkService linkService) {
        return new JooqSubscriptionService(repository, linkService);
    }

    @Bean
    public LinkUpdater linkUpdater(
            JooqLinkRepository repo, Scheduler scheduler, BotService bot,
            GitHubService github, StackOverflowService stackoverflow) {
        return new LinkUpdater(repo, scheduler, bot, github, stackoverflow);
    }
}
