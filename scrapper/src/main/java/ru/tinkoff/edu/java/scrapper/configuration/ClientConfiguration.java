package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.tinkoff.edu.java.scrapper.client.GitHubClient;
import ru.tinkoff.edu.java.scrapper.client.StackOverflowClient;

@Configuration
public class ClientConfiguration {

    private <WebClientBased> WebClientBased buildWebClient(Class<WebClientBased> clazz, String baseUrl) {
        var client = WebClient.builder().baseUrl(baseUrl).build();
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
}
