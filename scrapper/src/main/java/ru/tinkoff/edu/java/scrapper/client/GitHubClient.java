package ru.tinkoff.edu.java.scrapper.client;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.github.GitHubEventResponse;

import java.util.List;

@HttpExchange(accept = "application/vnd.github+json", contentType = MediaType.APPLICATION_JSON_VALUE)
public interface GitHubClient {
    @GetExchange("/repos/{owner}/{repo}/events")
    Mono<List<GitHubEventResponse>> fetchEvents(@PathVariable("owner") String author, @PathVariable("repo") String repo);
}
