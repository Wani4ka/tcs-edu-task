package ru.tinkoff.edu.java.bot.dto;

import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.validation.annotation.Validated;

import java.net.URI;
import java.util.List;

@Validated
public record LinkUpdateRequest(
        @PositiveOrZero
        long id,
        URI url,
        String description,
        List<Long> tgChatIds
) {
}
