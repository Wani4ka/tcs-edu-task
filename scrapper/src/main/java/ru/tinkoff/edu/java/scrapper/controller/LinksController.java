package ru.tinkoff.edu.java.scrapper.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.dto.RemoveLinkRequest;

import java.util.List;

@RestController
public class LinksController {

    @GetMapping("/links")
    public ListLinksResponse listLinks(@PositiveOrZero @RequestHeader("Tg-Chat-Id") long tgChatId) {
        return new ListLinksResponse(List.of(new LinkResponse(1, "https://github.com/")), 1);
    }

    @PostMapping("/links")
    public LinkResponse addLink(@PositiveOrZero @RequestHeader("Tg-Chat-Id") long tgChatId, @Valid @RequestBody AddLinkRequest req) {
        return new LinkResponse(1, req.link());
    }

    @DeleteMapping("/links")
    public LinkResponse deleteLink(@PositiveOrZero @RequestHeader("Tg-Chat-Id") long tgChatId, @Valid @RequestBody RemoveLinkRequest req) {
        return new LinkResponse(1, req.link());
    }
}
