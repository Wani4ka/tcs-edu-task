package ru.tinkoff.edu.java.scrapper.controller;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.edu.java.scrapper.service.ChatService;

@RestController
@RequiredArgsConstructor
public class TgChatController {

    private final ChatService service;

    @PostMapping("/tg-chat/{id}")
    public void registerChat(@PositiveOrZero @PathVariable long id) {
        service.register(id);
    }

    @DeleteMapping("/tg-chat/{id}")
    public void deleteChat(@PositiveOrZero @PathVariable long id) {
        service.unregister(id);
    }
}
