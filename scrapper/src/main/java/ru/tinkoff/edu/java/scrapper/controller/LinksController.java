package ru.tinkoff.edu.java.scrapper.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.dto.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.service.SubscriptionService;

@RestController
@RequiredArgsConstructor
public class LinksController {

    private final SubscriptionService subscriptions;

    @GetMapping("/links")
    public ListLinksResponse listLinks(@PositiveOrZero @RequestHeader("Tg-Chat-Id") long tgChatId) {
        var links = subscriptions.getSubscriptions(tgChatId)
                .stream().map(l -> new LinkResponse(l.getId(), l.getUrl())).toList();
        return new ListLinksResponse(links, links.size());
    }

    @PostMapping("/links")
    public LinkResponse addLink(@PositiveOrZero @RequestHeader("Tg-Chat-Id") long tgChatId, @Valid @RequestBody AddLinkRequest req) {
        var link = subscriptions.subscribe(tgChatId, req.link());
        return new LinkResponse(link.getId(), link.getUrl());
    }

    @DeleteMapping("/links")
    public LinkResponse deleteLink(@PositiveOrZero @RequestHeader("Tg-Chat-Id") long tgChatId, @Valid @RequestBody RemoveLinkRequest req) {
        var link = subscriptions.unsubscribe(tgChatId, req.link());
        return new LinkResponse(link.getId(), link.getUrl());
    }
}
