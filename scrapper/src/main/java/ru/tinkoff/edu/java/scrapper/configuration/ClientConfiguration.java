package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.tinkoff.edu.java.scrapper.client.BotClient;
import ru.tinkoff.edu.java.scrapper.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;

@Configuration
public class ClientConfiguration {
    private static final int MAX_RESPONSE_SIZE = 16 * 1024 * 1024;

    private <W> W buildWebClient(Class<W> clazz, String baseUrl) {
        var strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(MAX_RESPONSE_SIZE))
                .build();
        var client = WebClient.builder().baseUrl(baseUrl).exchangeStrategies(strategies).build();
        var proxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return proxyFactory.createClient(clazz);
    }

    @Bean
    public GitHubClient getGitHubClient(ApplicationConfig config) {
        return buildWebClient(GitHubClient.class, config.github().getBaseUrl());
    }

    @Bean
    public StackOverflowClient getStackOverflowClient(ApplicationConfig config) {
        return buildWebClient(StackOverflowClient.class, config.stackoverflow().getBaseUrl());
    }

    @Bean
    public BotClient getBotClient(ApplicationConfig config) {
        return buildWebClient(BotClient.class, config.bot().getBaseUrl());
    }
}
