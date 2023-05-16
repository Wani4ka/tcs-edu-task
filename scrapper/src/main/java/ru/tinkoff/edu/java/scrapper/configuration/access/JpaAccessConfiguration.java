package ru.tinkoff.edu.java.scrapper.configuration.access;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.configuration.Scheduler;
import ru.tinkoff.edu.java.scrapper.domain.repository.jpa.JpaChatRepository;
import ru.tinkoff.edu.java.scrapper.domain.repository.jpa.JpaLinkRepository;
import ru.tinkoff.edu.java.scrapper.domain.repository.jpa.JpaSubscriptionRepository;
import ru.tinkoff.edu.java.scrapper.service.*;
import ru.tinkoff.edu.java.scrapper.service.external.GitHubService;
import ru.tinkoff.edu.java.scrapper.service.external.StackOverflowService;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaChatService;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaLinkService;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaSubscriptionService;

@Configuration
@Slf4j
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class JpaAccessConfiguration {

    public JpaAccessConfiguration() {
        log.info("Using JPA access type");
    }

    @Bean
    public ChatService chatService(JpaChatRepository repository) {
        return new JpaChatService(repository);
    }

    @Bean
    public LinkService linkService(JpaLinkRepository repository) {
        return new JpaLinkService(repository);
    }

    @Bean
    public SubscriptionService subscriptionService(JpaSubscriptionRepository repository, LinkService linkService) {
        return new JpaSubscriptionService(repository, linkService);
    }

    @Bean
    public LinkUpdater linkUpdater(
            JpaLinkRepository repo, Scheduler scheduler, BotService bot,
            GitHubService github, StackOverflowService stackoverflow) {
        return new LinkUpdater(repo, scheduler, bot, github, stackoverflow);
    }

}
