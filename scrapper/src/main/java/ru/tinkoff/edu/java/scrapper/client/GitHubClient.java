package ru.tinkoff.edu.java.scrapper.client;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.GitHubRepoResponse;

@HttpExchange(accept = "application/vnd.github+json", contentType = MediaType.APPLICATION_JSON_VALUE)
public interface GitHubClient {
    @GetExchange("/repos/{owner}/{repo}")
    Mono<GitHubRepoResponse> fetchRepository(@PathVariable("owner") String author, @PathVariable("repo") String repo);
}
