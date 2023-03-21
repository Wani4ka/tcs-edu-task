package ru.tinkoff.edu.java.bot.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.URL;

import java.util.List;

public record LinkUpdateRequest(
        @PositiveOrZero
        long id,
        @URL
        String url,
        @NotEmpty
        String description,
        @NotEmpty
        List<Integer> tgChatIds
) {
}
