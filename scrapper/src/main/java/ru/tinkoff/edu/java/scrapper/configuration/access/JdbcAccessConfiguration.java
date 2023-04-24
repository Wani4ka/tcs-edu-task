package ru.tinkoff.edu.java.scrapper.configuration.access;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.configuration.Scheduler;
import ru.tinkoff.edu.java.scrapper.domain.repository.jdbc.JdbcChatRepository;
import ru.tinkoff.edu.java.scrapper.domain.repository.jdbc.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.domain.repository.jdbc.JdbcSubscriptionRepository;
import ru.tinkoff.edu.java.scrapper.service.*;
import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcChatService;
import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcLinkService;
import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcLinkUpdater;
import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcSubscriptionService;

@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
public class JdbcAccessConfiguration {

    public JdbcAccessConfiguration() {
        log.info("Using JDBC access type");
    }

    @Bean
    public LinkService linkService(JdbcLinkRepository repository) {
        return new JdbcLinkService(repository);
    }

    @Bean
    public ChatService chatService(JdbcChatRepository repository) {
        return new JdbcChatService(repository);
    }

    @Bean
    public SubscriptionService subscriptionService(JdbcSubscriptionRepository repository, LinkService linkService) {
        return new JdbcSubscriptionService(repository, linkService);
    }

    @Bean
    public LinkUpdater linkUpdater(JdbcLinkRepository repository, Scheduler scheduler, BotService botService, GitHubService ghService, StackOverflowService soService) {
        return new JdbcLinkUpdater(repository, scheduler, botService, ghService, soService);
    }
}
