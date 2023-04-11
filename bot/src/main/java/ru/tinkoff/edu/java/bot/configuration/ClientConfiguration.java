package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.tinkoff.edu.java.bot.client.ScrapperClient;

@Configuration
public class ClientConfiguration {
    private <WebClientBased> WebClientBased buildWebClient(Class<WebClientBased> clazz, String baseUrl) {
        var client = WebClient.builder().baseUrl(baseUrl).build();
        var proxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return proxyFactory.createClient(clazz);
    }

    @Bean
    public ScrapperClient getScrapperClient(ApplicationConfig config) {
        return buildWebClient(ScrapperClient.class, config.scrapper().baseUrl());
    }
}
