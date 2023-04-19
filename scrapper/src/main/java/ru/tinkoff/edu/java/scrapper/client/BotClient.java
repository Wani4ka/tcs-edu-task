package ru.tinkoff.edu.java.scrapper.client;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import ru.tinkoff.edu.java.scrapper.dto.LinkUpdateRequest;

@HttpExchange(accept = MediaType.APPLICATION_JSON_VALUE, contentType = MediaType.APPLICATION_JSON_VALUE)
public interface BotClient {
    @PostExchange("/updates")
    void sendUpdate(@Valid @RequestBody LinkUpdateRequest update);
}
