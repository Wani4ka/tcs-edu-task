package ru.tinkoff.edu.java.scrapper.controller;

import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TgChatController {

    @PostMapping("/tg-chat/{id}")
    public void registerChat(@PositiveOrZero @PathVariable long id) {

    }

    @DeleteMapping("/tg-chat/{id}")
    public void deleteChat(@PositiveOrZero @PathVariable long id) {

    }
}
