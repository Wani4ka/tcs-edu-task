package ru.tinkoff.edu.java.bot.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import ru.tinkoff.edu.java.bot.dto.AddLinkRequest;
import ru.tinkoff.edu.java.bot.dto.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.ListLinksResponse;
import ru.tinkoff.edu.java.bot.dto.RemoveLinkRequest;

@HttpExchange(accept = MediaType.APPLICATION_JSON_VALUE, contentType = MediaType.APPLICATION_JSON_VALUE)
public interface ScrapperClient {
    @GetExchange("/links")
    ListLinksResponse listLinks(@PositiveOrZero @RequestHeader("Tg-Chat-Id") long tgChatId);

    @PostExchange("/links")
    LinkResponse addLink(
        @PositiveOrZero @RequestHeader("Tg-Chat-Id") long tgChatId, @Valid @RequestBody AddLinkRequest req);

    @DeleteExchange("/links")
    LinkResponse deleteLink(
        @PositiveOrZero @RequestHeader("Tg-Chat-Id") long tgChatId, @Valid @RequestBody RemoveLinkRequest req);

    @PostExchange("/tg-chat/{id}")
    void registerChat(@PositiveOrZero @PathVariable long id);

    @DeleteExchange("/tg-chat/{id}")
    void deleteChat(@PositiveOrZero @PathVariable long id);
}
