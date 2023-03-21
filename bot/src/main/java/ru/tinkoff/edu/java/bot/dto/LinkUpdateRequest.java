package ru.tinkoff.edu.java.bot.dto;

import jakarta.validation.constraints.PositiveOrZero;

import java.net.URL;
import java.util.List;

public record LinkUpdateRequest(
        @PositiveOrZero
        long id,
        URL url,
        String description,
        List<Long> tgChatIds
) {
}
